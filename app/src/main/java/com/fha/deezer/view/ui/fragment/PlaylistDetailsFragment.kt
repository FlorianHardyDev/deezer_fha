package com.fha.deezer.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fha.deezer.R
import com.fha.deezer.service.model.Playlist
import com.fha.deezer.service.responses.PlaylistResponse
import com.fha.deezer.utils.DurationHelper
import com.fha.deezer.view.adapter.TrackAdapter
import com.fha.deezer.viewmodel.PlaylistViewModel
import kotlinx.android.synthetic.main.fragment_playlist_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlaylistDetailsFragment : Fragment() {

    lateinit var playlist: Playlist

    private val playlistModel: PlaylistViewModel by viewModel()

    private var alertDialog: AlertDialog? = null

    companion object {
        const val PLAYLIST_ID = "PLAYLIST_ID"

        fun newInstance(playlist: Playlist): PlaylistDetailsFragment {
            val args = Bundle()
            args.putSerializable(PLAYLIST_ID, playlist)
            val fragment = PlaylistDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlist_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { playlist = it.getSerializable(PLAYLIST_ID) as Playlist }
    }

    override fun onPause() {
        hideProgressBar()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()

        collapsingToolbar.title = " "
        imageviewPlaylistHeader.setImageURI(playlist.pictureXl)
        textviewHeaderPlaylistTitle.text = playlist.title
        playlist.creator?.let { creator ->
            textviewHeaderPlaylistAuthor.text = String.format(getString(R.string.playlist_author_format), creator.name)
        }
        textviewHeaderPlaylistDuration.text = DurationHelper().formatSeconds(playlist.duration.toString())

        recyclerViewTracks.layoutManager = LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)

        playlist.id?.let { playlistId ->
            getPlaylistDetails(playlistId)
            observePlaylistDetails(playlistId)
        }
    }

    private fun observePlaylistDetails(playlistId: String) {
        playlistModel.playlistResponse.observe(this, Observer(function = fun(playlistResponse: PlaylistResponse?) {
            playlistResponse?.let { response ->
                response.throwable?.let { throwable ->
                    throwable.localizedMessage?.let { message ->
                        hideProgressBar()
                        this.context?.let { ctx ->
                            alertDialog = AlertDialog.Builder(ctx, R.style.Theme_AppCompat_Dialog_Alert)
                                .setTitle(R.string.ws_error_playlist)
                                .setMessage(message)
                                .setNeutralButton(R.string.actions_retry) { _, _ ->
                                    getPlaylistDetails(playlistId)
                                }
                                .create()
                            alertDialog?.show()
                        }
                    }
                }
                response.playlist?.tracks?.data?.let { tracksData ->
                    if (tracksData.isNotEmpty()) hideProgressBar()
                    val trackAdapter = TrackAdapter(tracksData)
                    recyclerViewTracks.adapter = trackAdapter
                }
            }
        }))
    }

    private fun getPlaylistDetails(playlistId: String) {
        showProgressBar()
        playlistModel.getPlaylist(playlistId)
    }

    private fun showProgressBar() {
        this.activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBarPlaylist?.visibility = View.VISIBLE

    }

    private fun hideProgressBar() {
        this.alertDialog?.dismiss()
        this.activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBarPlaylist?.visibility = View.GONE
    }
}

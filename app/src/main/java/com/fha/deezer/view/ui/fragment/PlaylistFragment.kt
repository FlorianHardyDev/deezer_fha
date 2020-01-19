package com.fha.deezer.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fha.deezer.R
import com.fha.deezer.service.responses.PlaylistListResponse
import com.fha.deezer.view.adapter.PlaylistAdapter
import com.fha.deezer.viewmodel.PlaylistListViewModel
import kotlinx.android.synthetic.main.fragment_playlist.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlaylistFragment : Fragment() {

    private val playlistListModel: PlaylistListViewModel by viewModel()

    private var alertDialog: AlertDialog? = null

    companion object {
        fun newInstance() = PlaylistFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onPause() {
        hideProgressBar()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()

        recyclerView.layoutManager = GridLayoutManager(view?.context, 3, RecyclerView.VERTICAL, false)

        getPlaylist()

        observePlaylist()
    }

    private fun observePlaylist() {
        playlistListModel.playlistResponse.observe(this, Observer(function = fun(playlistListResponse: PlaylistListResponse?) {
            playlistListResponse?.let { response ->
                response.throwable?.let { throwable ->
                    throwable.localizedMessage?.let { message ->
                        hideProgressBar()
                        this.context?.let { ctx ->
                            alertDialog = AlertDialog.Builder(ctx, R.style.Theme_AppCompat_Dialog_Alert)
                                .setTitle(R.string.ws_error_playlist_list)
                                .setMessage(message)
                                .setNeutralButton(R.string.actions_retry)  { _, _ -> getPlaylist() }
                                .setCancelable(false)
                                .create()
                            alertDialog?.show()
                        }
                    }
                }

                response.playlistList?.let { list ->
                    if (list.isNotEmpty()) hideProgressBar()
                    val playlistListAdapter = PlaylistAdapter(list)
                    recyclerView.adapter = playlistListAdapter
                    playlistListAdapter.setItemClickListener(object : PlaylistAdapter.ItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            val newFragment = PlaylistDetailsFragment.newInstance(list[position])
                            val transaction = fragmentManager?.beginTransaction()
                            transaction?.replace(R.id.fragment_container, newFragment)
                            transaction?.addToBackStack(null)
                            transaction?.commit()
                        }
                    })
                }
            }
        }))
    }

    private fun getPlaylist() {
        showProgressBar()
        playlistListModel.getPlaylistList("5")
    }

    private fun showProgressBar() {
        this.activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBarPlaylistList?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        this.alertDialog?.dismiss()
        this.activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBarPlaylistList?.visibility = View.GONE
    }
}

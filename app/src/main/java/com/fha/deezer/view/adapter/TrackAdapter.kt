package com.fha.deezer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.common.util.UriUtil
import com.facebook.drawee.view.SimpleDraweeView
import com.fha.deezer.R
import com.fha.deezer.service.model.Track
import com.fha.deezer.utils.DurationHelper

class TrackAdapter(private val trackList: List<Track>) :
    RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_tracks, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title?.text = trackList[position].title
        trackList[position].artist?.let { artist ->
            viewHolder.artist?.text = artist.name
        }
        trackList[position].duration?.let { duration ->
            viewHolder.duration?.text = DurationHelper().formatSeconds(duration)
        }
        trackList[position].album?.let { album ->
            album.cover?.let { cover ->
                viewHolder.imageView?.setImageURI(cover)
            } ?: run {
                // /!\ This method is deprecated for resources
                viewHolder.imageView?.setImageURI(UriUtil.getUriForResourceId(R.drawable.placeholder))
            }
        }
    }

    override fun getItemCount() = trackList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView? = itemView.findViewById(R.id.textview_track_title)
        val artist: TextView? = itemView.findViewById(R.id.textview_artist_name)
        val duration: TextView? = itemView.findViewById(R.id.textview_track_duration)
        val imageView: SimpleDraweeView? = itemView.findViewById(R.id.imageview_track)
    }
}
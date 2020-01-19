package com.fha.deezer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.fha.deezer.R
import com.fha.deezer.service.model.Playlist

class PlaylistAdapter(private val playlistList: List<Playlist>) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_playlist, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name?.text = playlistList[position].title
        viewHolder.imageView?.setImageURI(playlistList[position].picture)
        viewHolder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(it, position)
        }
    }

    override fun getItemCount() = playlistList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.textview_playlist_name)
        val imageView: SimpleDraweeView? = itemView.findViewById(R.id.imageview_playlist)
    }

    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}
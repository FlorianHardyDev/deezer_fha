package com.fha.deezer.service.repository

import com.fha.deezer.service.model.Playlist
import retrofit2.Call
import retrofit2.Response

class PlaylistRepository(val service: DeezerService) {
    /**
     * Get the playlist details
     *
     * @playlistId the playlist id (query parameter)
     * @onData response interface
     */
    fun getPlaylist(playlistId: String, onData: OnData) {
        service.getPlaylistDetails(playlistId).enqueue(object : retrofit2.Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                onData.onSuccess((response.body() as Playlist))
            }

            override fun onFailure(call: Call<Playlist>, throwable: Throwable) {
                onData.onFailure(throwable)
            }
        })
    }

    interface OnData {
        fun onSuccess(data: Playlist)
        fun onFailure(throwable: Throwable)
    }
}
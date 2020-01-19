package com.fha.deezer.service.repository

import com.fha.deezer.service.model.PlaylistData
import retrofit2.Call
import retrofit2.Response

class PlaylistListRepository(val service: DeezerService) {
    /**
     * Get the list of playlist by user id
     *
     * @userId the playlist id (query parameter)
     * @onData response interface
     */
    fun getPlaylistList(userId: String, onData: OnData) {
        service.getPlaylistList(userId).enqueue(object : retrofit2.Callback<PlaylistData> {
            override fun onResponse(call: Call<PlaylistData>, response: Response<PlaylistData>) {
                onData.onSuccess((response.body() as PlaylistData))
            }

            override fun onFailure(call: Call<PlaylistData>, throwable: Throwable) {
                onData.onFailure(throwable)
            }
        })
    }

    interface OnData {
        fun onSuccess(data: PlaylistData)
        fun onFailure(throwable: Throwable)
    }
}
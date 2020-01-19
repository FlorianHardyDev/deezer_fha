package com.fha.deezer.service.repository

import com.fha.deezer.service.model.Playlist
import com.fha.deezer.service.model.PlaylistData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerService {
    @GET("user/{userId}/playlists")
    fun getPlaylistList(@Path("userId") user: String?): Call<PlaylistData>

    @GET("/playlist/{playlistId}")
    fun getPlaylistDetails(@Path("playlistId") playlistId: String?): Call<Playlist>
}
package com.fha.deezer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fha.deezer.service.model.Playlist
import com.fha.deezer.service.repository.PlaylistRepository
import com.fha.deezer.service.responses.PlaylistResponse

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel() {

    var playlistResponse = MutableLiveData<PlaylistResponse>()

    init {
        playlistResponse.value = PlaylistResponse(null, Throwable())
    }

    fun getPlaylist(playlistId: String) {
        repository.getPlaylist(playlistId, object : PlaylistRepository.OnData {
            override fun onSuccess(data: Playlist) {
                playlistResponse.value = PlaylistResponse(data, Throwable())
            }

            override fun onFailure(throwable: Throwable) {
                playlistResponse.value = PlaylistResponse(null, throwable)
            }
        })
    }
}
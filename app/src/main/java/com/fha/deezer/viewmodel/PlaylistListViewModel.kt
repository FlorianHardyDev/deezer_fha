package com.fha.deezer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fha.deezer.service.model.PlaylistData
import com.fha.deezer.service.repository.PlaylistListRepository
import com.fha.deezer.service.responses.PlaylistListResponse

class PlaylistListViewModel(private val repository: PlaylistListRepository) : ViewModel() {

    var playlistResponse = MutableLiveData<PlaylistListResponse>()

    init {
        playlistResponse.value = PlaylistListResponse(null, null)
    }

    /**
     * Get the list of playlist by user id
     *
     * @userId the user id (query parameter)
     */
    fun getPlaylistList(userId: String) {
        repository.getPlaylistList(userId, object : PlaylistListRepository.OnData {
            override fun onSuccess(data: PlaylistData) {
                playlistResponse.value = PlaylistListResponse(data.data, null)
            }

            override fun onFailure(throwable: Throwable) {
                playlistResponse.value = PlaylistListResponse(null, throwable)
            }
        })
    }
}
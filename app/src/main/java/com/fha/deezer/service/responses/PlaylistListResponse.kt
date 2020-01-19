package com.fha.deezer.service.responses

import com.fha.deezer.service.model.Playlist

data class PlaylistListResponse(var playlistList: List<Playlist>?, var throwable: Throwable?)

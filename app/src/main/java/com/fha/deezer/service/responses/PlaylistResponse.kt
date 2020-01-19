package com.fha.deezer.service.responses

import com.fha.deezer.service.model.Playlist

data class PlaylistResponse(var playlist: Playlist?, var throwable: Throwable?)

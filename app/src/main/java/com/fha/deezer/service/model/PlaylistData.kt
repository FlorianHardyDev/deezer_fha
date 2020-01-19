package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaylistData(
    @SerializedName("data") val data: List<Playlist>?,
    @SerializedName("checksum") val checksum: String?,
    @SerializedName("total") val total: String?,
    @SerializedName("next") val next: String?
) : Serializable
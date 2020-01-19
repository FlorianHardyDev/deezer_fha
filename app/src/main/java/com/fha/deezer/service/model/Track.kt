package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Track(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("rank") val rank: String?,
    @SerializedName("artist") val artist: Artist?,
    @SerializedName("album") val album: Album?,
    @SerializedName("type") val type: String?
) : Serializable

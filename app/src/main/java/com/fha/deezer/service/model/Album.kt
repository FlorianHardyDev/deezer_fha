package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Album(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("cover") val cover: String?,
    @SerializedName("tracklist") val tracklist: String?,
    @SerializedName("type") val type: String?
) : Serializable

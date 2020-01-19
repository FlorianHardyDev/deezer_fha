package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Artist(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("tracklist") val tracklist: String?,
    @SerializedName("type") val type: String?
) : Serializable

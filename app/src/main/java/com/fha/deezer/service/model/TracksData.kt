package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TracksData(
    @SerializedName("data") val data: List<Track>?,
    @SerializedName("checksum") val checksum: String?
) : Serializable
package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Class which provides a model for Playlist
 * @constructor Sets all properties of the user
 * @id The id of the user
 * @name The name of the user
 */
data class User (
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
) : Serializable
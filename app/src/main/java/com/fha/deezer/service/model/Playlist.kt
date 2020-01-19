package com.fha.deezer.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Class which provides a model for Playlist
 * @constructor Sets all properties of the playlist
 * @id The id of the playlist
 * @title The title of the playlist
 * @desc The description of the playlist
 * @nbTracks The number of tracks in the playlist
 * @picture The playlist cover image
 * @creator The creator of the playlist
 */
data class Playlist(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val desc: String?,
    @SerializedName("duration") val duration: Int?,
    @SerializedName("nb_tracks") val nbTracks: Int?,
    @SerializedName("fans") val nbFans: Int?,
    @SerializedName("link") val link: String?,
    @SerializedName("picture") val picture: String?,
    @SerializedName("picture_xl") val pictureXl: String?,
    @SerializedName("tracklist") val tracklist: String?,
    @SerializedName("creator") val creator: User?,
    @SerializedName("tracks") val tracks: TracksData?,
    @SerializedName("type") val type: String?
) : Serializable

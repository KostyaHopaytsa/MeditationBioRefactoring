package com.example.meditationbiorefactoring.feature_music.data.remote.dto

import com.example.meditationbiorefactoring.feature_music.domain.model.Track
import com.squareup.moshi.Json

data class TrackResponseDto(
    val results: List<TrackDto>
)

data class TrackDto(
    val id: String,
    val name: String,
    @Json(name = "artist_name")
    val artistName: String,
    @Json(name = "album_name")
    val albumName: String?,
    val image: String?,
    val audio: String
)

fun TrackDto.toTrack(): Track {
    return Track(
        id = id,
        title = name,
        artist = artistName,
        album = albumName,
        imageUrl = image,
        audioUrl = audio
    )
}
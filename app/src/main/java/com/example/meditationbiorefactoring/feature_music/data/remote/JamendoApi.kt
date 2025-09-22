package com.example.meditationbiorefactoring.feature_music.data.remote

import com.example.meditationbiorefactoring.common.Constants
import com.example.meditationbiorefactoring.feature_music.data.remote.dto.TrackResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface JamendoApi {

    @GET("tracks")
    suspend fun getTracksByTag(
        @Query("client_id") clientId: String = Constants.CLIENT_ID,
        @Query("tags") tag: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 10,
        @Query("include") include: String = "musicinfo",
        @Query("audioformat") audioFormat: String = "mp32"
    ): TrackResponseDto
}
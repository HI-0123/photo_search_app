package com.example.photosearchapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val color: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    val height: Int?,
    val id: String?,
    val likes: Int?,
    val links: Links?,
    val urls: Urls?,
    val user: User?,
    val width: Int?
)
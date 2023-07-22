package com.example.photosearchapp.domain.model

data class Photo(
    val photoId: String,
    val likes: Int?,
    val imageUrl: String,
    val photographer: String?,
)

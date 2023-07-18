package com.example.photosearchapp.domain.repository

import com.example.photosearchapp.data.remote.SearchPhotosResultDto

interface PhotoRepository {

    suspend fun searchPhotos(query: String) : SearchPhotosResultDto
}
package com.example.photosearchapp.data.repository

import com.example.photosearchapp.data.remote.SearchPhotosResultDto
import com.example.photosearchapp.data.remote.UnsplashApi
import com.example.photosearchapp.domain.repository.PhotoRepository
import javax.inject.Inject


class PhotoRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
) : PhotoRepository {

    override suspend fun searchPhotos(query: String): SearchPhotosResultDto {
        return api.searchPhotos(query)
    }


}
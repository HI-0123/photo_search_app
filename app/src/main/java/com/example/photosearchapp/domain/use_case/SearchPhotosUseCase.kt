package com.example.photosearchapp.domain.use_case

import com.example.photosearchapp.common.NetworkResponse
import com.example.photosearchapp.data.remote.toPhotos
import com.example.photosearchapp.domain.model.Photo
import com.example.photosearchapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository,
) {
    operator fun invoke(query: String): Flow<NetworkResponse<List<Photo>>> = flow {
        try {
            emit(NetworkResponse.Loading<List<Photo>>())
            val photos = repository.searchPhotos(query).toPhotos()
            emit(NetworkResponse.Success<List<Photo>>(photos))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<List<Photo>>(e.message.toString()))
        }
    }
}
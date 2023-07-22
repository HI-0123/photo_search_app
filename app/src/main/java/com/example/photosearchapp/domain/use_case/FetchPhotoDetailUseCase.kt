package com.example.photosearchapp.domain.use_case

import com.example.photosearchapp.common.NetworkResponse
import com.example.photosearchapp.data.remote.toPhotoDetail
import com.example.photosearchapp.domain.model.PhotoDetail
import com.example.photosearchapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FetchPhotoDetailUseCase @Inject constructor(
    private val repository: PhotoRepository,
) {
    operator fun invoke(photoId: String): Flow<NetworkResponse<PhotoDetail>> = flow {
        try {
            emit(NetworkResponse.Loading<PhotoDetail>())
            val photo = repository.getPhotoById(photoId).toPhotoDetail()
            emit(NetworkResponse.Success<PhotoDetail>(photo))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<PhotoDetail>(e.message.toString()))
        }
    }
}
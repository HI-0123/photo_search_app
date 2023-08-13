package com.example.photosearchapp.presentation.photo_detail

import com.example.photosearchapp.domain.model.PhotoDetail

data class PhotoDetailState(
    val isLoading: Boolean = false,
    val photoDetail: PhotoDetail? = null,
    val error: String? = null,
)

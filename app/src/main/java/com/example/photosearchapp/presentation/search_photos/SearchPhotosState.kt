package com.example.photosearchapp.presentation.search_photos

import com.example.photosearchapp.domain.model.Photo

data class SearchPhotosState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null,
    )

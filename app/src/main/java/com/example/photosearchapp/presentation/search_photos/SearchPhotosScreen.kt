package com.example.photosearchapp.presentation.search_photos

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchPhotoScreen(
    viewModel: SearchPhotosViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val photos = state.photos

    LazyColumn {
        items(photos.count()) { index ->
            Text(photos[index].imageUrl)
        }
    }
}
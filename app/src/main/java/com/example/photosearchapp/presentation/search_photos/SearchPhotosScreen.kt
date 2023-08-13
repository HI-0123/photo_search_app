package com.example.photosearchapp.presentation.search_photos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.photosearchapp.presentation.ScreenRoute
import com.example.photosearchapp.presentation.search_photos.components.PhotoThumbnail
import com.example.photosearchapp.presentation.search_photos.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotoScreen(
    navController: NavController, viewModel: SearchPhotosViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val photos = state.photos

    Scaffold() { paddingValue ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
        ) {
            Column(modifier = Modifier.align(alignment = Center)) {
                when {
                    state.isLoading -> {
                        // ローディング
                        CircularProgressIndicator()
                    }

                    !state.error.isNullOrBlank() -> {
                        // エラー表示
                        Text(
                            text = state.error,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }

                    else -> {
                        Box(modifier = Modifier.padding(horizontal = 2.dp)) {
                            SearchBar(
                                searchText = viewModel.query,
                                onSearchTextChanged = { viewModel.query = it },
                                onDone = { viewModel.searchPhotos() },
                            )
                        }

                        LazyColumn {
                            items(photos.count()) { index ->
                                val photo = photos[index]
                                PhotoThumbnail(
                                    photo = photo,
                                    onClick = {
                                        navController.navigate(ScreenRoute.PhotoDetailScreen.route + "/${photo.photoId}")
                                    },
                                )
                            }
                        }
                    }

                }
            }
        }

    }


}

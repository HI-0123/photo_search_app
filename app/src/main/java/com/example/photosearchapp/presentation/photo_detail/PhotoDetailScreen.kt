package com.example.photosearchapp.presentation.photo_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.photosearchapp.domain.model.PhotoDetail
import com.example.photosearchapp.presentation.photo_detail.components.CountLabel

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.Center),
                )
            }

            !state.error.isNullOrBlank() -> {
                // エラー表示
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                )
            }

            else -> {
                state.photoDetail?.let { photoDetail ->
                    PhotoDetailContent(photoDetail)
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun PhotoDetailContent(
    photoDetail: PhotoDetail,
) {
    Column(modifier = Modifier) {
        Box(
            modifier = Modifier.heightIn(200.dp)
        ) {
            var isLoading by remember { mutableStateOf(true) }
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
            }

            AsyncImage(model = photoDetail.imageUrl,
                contentDescription = photoDetail.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEndPercent = 5,
                            bottomStartPercent = 5,
                        ),
                    ),
                onSuccess = { isLoading = false })
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = photoDetail.description ?: "No description",
                color = Color.White,
                fontSize = 20.sp,
            )
            Text(
                text = photoDetail.photographer ?: "Unknown photographer",
                color = Color.White,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(24.dp))
            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = photoDetail.likes ?: 0,
                iconColor = Color.Magenta,

                )
            CountLabel(
                imageVector = Icons.Default.Share,
                count = photoDetail.downloadCount ?: 0,
                iconColor = Color.Green,

                )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Camera: ${photoDetail.camera}",
                color = Color.White,
            )
            Text(
                text = "Location: ${photoDetail.location}",
                color = Color.White,
            )
        }
    }
}

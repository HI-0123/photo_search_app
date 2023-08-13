package com.example.photosearchapp.presentation.search_photos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photosearchapp.domain.model.Photo
import com.example.photosearchapp.presentation.photo_detail.components.CountLabel

@Composable
fun PhotoThumbnail(
    photo: Photo,
    onClick: (Photo) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .heightIn(min = 200.dp)
            .clickable { onClick(photo) },
        contentAlignment = Alignment.BottomCenter,
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        AsyncImage(
            model = photo.imageUrl,
            contentDescription = photo.description,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                Text(
                    text = photo.description ?: "no description",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = photo.photographer ?: "no photographer",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CountLabel(
                imageVector = Icons.Default.Favorite,
                count = photo.likes ?: 0,
                iconColor = Color.Magenta,
            )
        }
    }
}

@Preview
@Composable
private fun PhotoThumbnailPreview() {
    val photo = Photo(
        photoId = "",
        description = "Image Preview",
        likes = 100,
        imageUrl = "https://unsplash.com/ja/%E5%86%99%E7%9C%9F/6kajEqr84iY",
        photographer = "Mailchimp",
    )
    PhotoThumbnail(
        photo = photo,
        onClick = {},
    )
}




package com.example.photosearchapp.presentation.photo_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CountLabel(
    imageVector: ImageVector,
    count: Int,
    iconColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Icon(
            imageVector = imageVector,
            contentDescription = "icon",
            tint = iconColor,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = count.toString(),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
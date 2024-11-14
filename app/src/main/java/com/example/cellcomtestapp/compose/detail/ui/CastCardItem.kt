package com.example.cellcomtestapp.compose.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.cellcomtestapp.BuildConfig
import com.example.cellcomtestapp.compose.core.components.progress.CircularProgressIndicatorContent
import com.example.cellcomtestapp.compose.theme.dp10
import com.example.cellcomtestapp.compose.theme.dp128
import com.example.cellcomtestapp.compose.theme.dp16
import com.example.cellcomtestapp.compose.theme.dp8
import com.example.domain.model.details.Cast

@Composable
fun CastCardItem(castItem: Cast) {
    Card(
        modifier = Modifier
            .padding(start = dp16)
            .background(color = Color.White)
            .clickable {

            },
        shape = RoundedCornerShape(dp10)
    ) {
        Column(
            modifier = Modifier.width(dp128)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.LARGE_IMAGE_URL + castItem.profilePath)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dp128),
                loading = {
                    CircularProgressIndicatorContent(isLoading = true)
                }

            )
            Spacer(modifier = Modifier.height(dp8))
            Text(
                text = castItem.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = dp8),
            )
            Text(
                text = castItem.character,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = dp8),
            )
            Spacer(modifier = Modifier.height(dp8))
        }
    }
}
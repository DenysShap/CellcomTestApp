package com.example.cellcomtestapp.compose.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.cellcomtestapp.BuildConfig
import com.example.cellcomtestapp.compose.core.components.progress.CircularProgressIndicatorContent
import com.example.cellcomtestapp.compose.home.model.MovieDetailsArgs
import com.example.cellcomtestapp.compose.theme.dp10
import com.example.cellcomtestapp.compose.theme.dp160
import com.example.cellcomtestapp.compose.theme.dp8
import com.example.domain.model.movies.MovieItem

@Composable
fun MovieItemCard(
    modifier: Modifier,
    item: MovieItem,
    onItemClick: (movieDetailsArgs: MovieDetailsArgs) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(dp8)
            .background(color = Color.White)
            .clickable {
                onItemClick(MovieDetailsArgs(item.id.toString(), item.title))
            },
        shape = RoundedCornerShape(dp10)
    ) {
        Column(
            modifier = modifier
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.ORIGINAL_IMAGE_URL + item.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dp160),
                loading = {
                    CircularProgressIndicatorContent(isLoading = true)
                }
            )
            Spacer(modifier = Modifier.height(dp8))
            Text(
                text = item.title,
                modifier = Modifier.padding(horizontal = dp8),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = item.releaseDate,
                modifier = Modifier.padding(horizontal = dp8)
            )
            Spacer(modifier = Modifier.height(dp8))
        }
    }
}
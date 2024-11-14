package com.example.cellcomtestapp.compose.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cellcomtestapp.BuildConfig
import com.example.cellcomtestapp.R
import com.example.cellcomtestapp.compose.core.components.error.CommonError
import com.example.cellcomtestapp.compose.detail.model.MovieDetailsEvent
import com.example.cellcomtestapp.compose.detail.model.MovieDetailsState
import com.example.cellcomtestapp.compose.detail.model.YoutubeVideoPlayerArgs
import com.example.cellcomtestapp.compose.theme.Purple40
import com.example.cellcomtestapp.compose.theme.dp10
import com.example.cellcomtestapp.compose.theme.dp135
import com.example.cellcomtestapp.compose.theme.dp16
import com.example.cellcomtestapp.compose.theme.dp180
import com.example.cellcomtestapp.compose.theme.dp2
import com.example.cellcomtestapp.compose.theme.dp220
import com.example.cellcomtestapp.compose.theme.dp48
import com.example.cellcomtestapp.compose.theme.dp8
import com.example.domain.model.details.Credits
import com.example.domain.model.details.Genres
import com.example.domain.model.details.MovieDetails

@Composable
fun MovieDetailsScreen(
    movieDetailsState: MovieDetailsState,
    onEvent: (MovieDetailsEvent) -> Unit,
    onViewTrailerClick: (youtubeVideoPlayerArgs: YoutubeVideoPlayerArgs) -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarNavigation(
                movieName = movieDetailsState.titleName,
                isFavourite = movieDetailsState.isFavourite,
                onEvent = onEvent,
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding() + dp8
            )
        ) {
            val details = movieDetailsState.movieDetails
            val cast = movieDetailsState.credits
            val playerCode = movieDetailsState.playerCode
            if (details?.id != null && cast?.id != null) {
                LazyColumn(content = {
                    item { ItemPlacard(details) }
                    item {
                        ItemTitle(
                            movieDetails = details,
                            playerCode = playerCode,
                            onViewTrailerClick = onViewTrailerClick
                        )
                    }
                    item { ItemOverview(details) }
                    item { ItemCast(cast) }
                })
            }
            if (movieDetailsState.isErrorMovieDetails) {
                CommonError()
            }
        }
    }
}

@Composable
fun ItemPlacard(movieDetails: MovieDetails) {
    Box(modifier = Modifier.padding(horizontal = dp16)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(BuildConfig.ORIGINAL_IMAGE_URL + movieDetails.backdropPath).crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(dp220)
                .padding(start = dp48)
                .clip(shape = RoundedCornerShape(dp10))
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(BuildConfig.ORIGINAL_IMAGE_URL + movieDetails.posterPath).crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(dp135)
                .height(dp180)
                .align(Alignment.CenterStart)
                .clip(shape = RoundedCornerShape(dp10))
        )
    }
}

@Composable
fun ItemTitle(
    movieDetails: MovieDetails,
    playerCode: String?,
    onViewTrailerClick: (youtubeVideoPlayerArgs: YoutubeVideoPlayerArgs) -> Unit,
) {
    Spacer(modifier = Modifier.height(dp16))
    Text(
        text = movieDetails.title,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = dp16)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dp16),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = movieDetails.shortInfoAboutFilm,
            maxLines = 1,
            modifier = Modifier.padding(top = dp8)
        )
    }

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        content = {
            item {
                movieDetails.genres?.let {
                    GenresItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dp8),
                        genres = it
                    )
                }
            }
        })

    Spacer(modifier = Modifier.height(dp8))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dp16),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AverageVoteRatingIndicator(movieDetails.voteAverage.toFloat().div(10))
        Text(
            text = stringResource(id = R.string.rating),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = dp10, end = dp16),
        )

        HorizontalDivider(
            modifier = Modifier
                .height(dp16)
                .width(dp2)
                .background(Color.Black)
        )

        Row(
            modifier = Modifier
                .clickable(onClick = {
                    playerCode?.let {
                        onViewTrailerClick(YoutubeVideoPlayerArgs(youtubeCode = it))
                    }
                })
                .padding(start = dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(dp16)
            )
            Text(
                text = stringResource(id = R.string.play_trailer),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                modifier = Modifier.padding(start = dp2, end = dp8)
            )
        }
    }
}

@Composable
fun ItemOverview(movieDetails: MovieDetails) {
    Spacer(modifier = Modifier.height(dp16))
    Text(
        text = stringResource(id = R.string.overview),
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        modifier = Modifier.padding(start = dp16)
    )
    Spacer(modifier = Modifier.height(dp8))
    Text(
        text = movieDetails.overview,
        modifier = Modifier.padding(horizontal = dp16)
    )
}

@Composable
fun ItemCast(credits: Credits) {
    Spacer(modifier = Modifier.height(dp16))
    Text(
        text = stringResource(id = R.string.cast),
        style = MaterialTheme.typography.headlineSmall,
        maxLines = 1,
        modifier = Modifier.padding(horizontal = dp16)
    )
    Spacer(modifier = Modifier.height(dp8))
    LazyRow(content = {
        credits.cast.forEach { cast ->
            item {
                CastCardItem(cast)
            }
        }
    })
    Spacer(modifier = Modifier.height(dp16))
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresItem(
    modifier: Modifier = Modifier,
    genres: List<Genres>,
) {
    FlowRow(
        modifier = modifier,
    ) {
        genres.forEach { genre ->
            Box(
                modifier = Modifier
                    .padding(end = dp8)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(.2f),
                        MaterialTheme.shapes.small
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = dp8, vertical = dp2),
                    text = genre.name,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigation(
    movieName: String,
    isFavourite: Boolean,
    onEvent: (MovieDetailsEvent) -> Unit,
    navigateBack: () -> Unit,
) {
    val icon = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.Favorite
    val tintColor = if (isFavourite) Color.Red else Color.Gray
    val event: MovieDetailsEvent =
        if (isFavourite) MovieDetailsEvent.RemoveFromFavourite else MovieDetailsEvent.AddToFavorites

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = movieName,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { onEvent(event) }) {
                Icon(
                    imageVector = icon,
                    tint = tintColor,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Purple40),
    )
}
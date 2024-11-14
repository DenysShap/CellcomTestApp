package com.example.cellcomtestapp.compose.favourite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.cellcomtestapp.BuildConfig
import com.example.cellcomtestapp.R
import com.example.cellcomtestapp.compose.core.components.empty.EmptyScreen
import com.example.cellcomtestapp.compose.core.components.progress.CircularProgressIndicatorContent
import com.example.cellcomtestapp.compose.detail.ui.AverageVoteRatingIndicator
import com.example.cellcomtestapp.compose.favourite.model.FavouriteEvent
import com.example.cellcomtestapp.compose.favourite.model.FavouriteMoviesState
import com.example.cellcomtestapp.compose.theme.Purple40
import com.example.cellcomtestapp.compose.theme.dp10
import com.example.cellcomtestapp.compose.theme.dp16
import com.example.cellcomtestapp.compose.theme.dp220
import com.example.cellcomtestapp.compose.theme.dp8
import com.example.domain.model.favourite.FavouriteItem

@Composable
fun FavouriteScreen(
    favouriteMoviesState: FavouriteMoviesState,
    onEvent: (FavouriteEvent) -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarFavouriteNavigation(
                title = stringResource(id = R.string.favourites),
                onEvent = onEvent,
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        if (favouriteMoviesState.isEmpty) {
            EmptyScreen(modifier = modifier)
        } else {
            FavoritesScreenContent(
                modifier = modifier,
                favoriteFilms = favouriteMoviesState.favouriteItems,
                isShowDeleteDialog = favouriteMoviesState.isShowRemoveDialog,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun FavoritesScreenContent(
    modifier: Modifier,
    favoriteFilms: List<FavouriteItem>,
    isShowDeleteDialog: Boolean,
    onEvent: (FavouriteEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(top = dp8),
        contentPadding = PaddingValues(horizontal = dp16),
        verticalArrangement = Arrangement.spacedBy(dp16)
    ) {
        items(favoriteFilms, key = { it.movieId }) { favorite ->
            FilmItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dp220),
                filmItem = favorite,
            )
        }
    }

    if (isShowDeleteDialog) {
        RemoveDialog(onEvent = onEvent)
    }
}

@Composable
fun RemoveDialog(onEvent: (FavouriteEvent) -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onEvent(FavouriteEvent.CancelRemoveDialog)
        },

        title = { Text(text = stringResource(id = R.string.delete_all_favorites_title)) },
        text = { Text(stringResource(id = R.string.delete_all_favorites_body)) },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(FavouriteEvent.RemoveAllFavouritesMovie)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.yes),
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onEvent(FavouriteEvent.CancelRemoveDialog) },
            ) {
                Text(text = stringResource(R.string.no))
            }
        },
        shape = RoundedCornerShape(dp10)
    )
}

@Composable
fun FilmItem(
    filmItem: FavouriteItem,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Box {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.ORIGINAL_IMAGE_URL + filmItem.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize(),
                loading = {
                    CircularProgressIndicatorContent(isLoading = true)
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0.5f, Transparent),
                                Pair(
                                    1.0f, MaterialTheme.colorScheme.background
                                )
                            )
                        )
                    )
            )

            MovieDetails(
                title = filmItem.title,
                releaseDate = filmItem.releaseDate,
                rating = filmItem.voteAverage
            )
        }
    }
}

@Composable
fun MovieDetails(
    title: String,
    releaseDate: String,
    rating: Double
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .padding(dp16)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = releaseDate,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Light,
                    ),
                )
            }
            AverageVoteRatingIndicator(rating.toFloat().div(10))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFavouriteNavigation(
    title: String,
    onEvent: (FavouriteEvent) -> Unit,
    navigateBack: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
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
            IconButton(onClick = { onEvent(FavouriteEvent.ShowRemoveDialog) }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Purple40),
    )
}
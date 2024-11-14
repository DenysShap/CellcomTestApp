package com.example.cellcomtestapp.compose.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.cellcomtestapp.compose.core.components.empty.EmptyScreen
import com.example.cellcomtestapp.compose.core.components.error.CommonError
import com.example.cellcomtestapp.compose.core.components.progress.CircularProgressIndicatorContent
import com.example.cellcomtestapp.compose.core.components.topbar.TopBar
import com.example.cellcomtestapp.compose.core.components.wrapper.ContentStateWrapper
import com.example.cellcomtestapp.compose.home.model.ChipUiItem
import com.example.cellcomtestapp.compose.home.model.HomeEvent
import com.example.cellcomtestapp.compose.home.model.HomeState
import com.example.cellcomtestapp.compose.home.model.MovieDetailsArgs
import com.example.cellcomtestapp.compose.theme.dp128
import com.example.cellcomtestapp.compose.theme.dp16
import com.example.cellcomtestapp.compose.theme.dp32
import com.example.cellcomtestapp.compose.theme.dp48
import com.example.cellcomtestapp.compose.theme.dp8
import com.example.domain.model.movies.MovieItem
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    homeState: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onItemClick: (movieDetailsArgs: MovieDetailsArgs) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = homeState.moviesType.resId))
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        ContentStateWrapper(
            modifier = modifier,
            isLoading = homeState.isLoading,
            isError = homeState.isError,
            isEmpty = homeState.isEmpty,
            loadingContent = {
                CircularProgressIndicatorContent(
                    modifier = modifier,
                    isLoading = true
                )
            },
            content = {
                Column {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        ChipsSection(chipItems = homeState.chipsUiItem, onEvent = onEvent)
                        MoviesList(
                            modifier = modifier,
                            moviesFlow = homeState.movieItemsFlow,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun MoviesList(
    modifier: Modifier,
    moviesFlow: Flow<PagingData<MovieItem>>,
    onItemClick: (movieDetailsArgs: MovieDetailsArgs) -> Unit,
) {
    val lazyPagingItems = moviesFlow.collectAsLazyPagingItems()
    val gridState = rememberLazyGridState()
    MovieGrid(
        modifier = modifier,
        gridState = gridState,
        movieItems = lazyPagingItems,
        onItemClick = onItemClick,
    )
}

@Composable
fun MovieGrid(
    modifier: Modifier,
    gridState: LazyGridState,
    movieItems: LazyPagingItems<MovieItem>,
    onItemClick: (movieDetailsArgs: MovieDetailsArgs) -> Unit,
) {
    val modifierApply =
        if (movieItems.loadState.append == LoadState.Loading)
            modifier.padding(bottom = dp48)
        else {
            modifier.fillMaxSize()
        }
    LazyVerticalGrid(
        modifier = modifierApply,
        state = gridState,
        columns = GridCells.Adaptive(dp128),
        content = {
            items(movieItems.itemCount) { i ->
                Row {
                    movieItems[i]?.let {
                        MovieItemCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            item = it,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        })
    if (movieItems.loadState.append == LoadState.Loading) {
        PaginationProgress()
    } else {
        HandlePagingResult(movieItems)
    }
}

@Composable
fun ChipsSection(chipItems: List<ChipUiItem>, onEvent: (HomeEvent) -> Unit) {
    LazyRow {
        items(chipItems) { chip ->
            FilterChip(
                modifier = Modifier.padding(vertical = dp16, horizontal = dp8),
                selected = chip.isSelected,
                onClick = { onEvent(HomeEvent.SelectCategory(chip.chipType)) },
                label = { Text(stringResource(id = chip.chipType.resId)) },
            )
        }
    }
}

@Composable
fun PaginationProgress() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dp16)
                .size(dp32)
        )
    }
}

@Composable
fun HandlePagingResult(
    movieItems: LazyPagingItems<MovieItem>,
) {
    movieItems.apply {
        val isFirstLoading = loadState.refresh is LoadState.Loading && itemCount == 0
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when {
            isFirstLoading -> CircularProgressIndicatorContent(isLoading = true)
            error != null -> CommonError()
            movieItems.itemCount == 0 -> EmptyScreen()
            else -> {}
        }
    }
}
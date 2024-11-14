package com.example.cellcomtestapp.compose.home.model

import androidx.paging.PagingData
import com.example.cellcomtestapp.compose.home.helper.provideChipUiItems
import com.example.domain.model.movies.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val moviesType: MoviesType = MoviesType.Popular,
    val chipsUiItem: List<ChipUiItem> = provideChipUiItems(),
    val movieItemsFlow: Flow<PagingData<MovieItem>> = emptyFlow(),
    val isEmpty: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
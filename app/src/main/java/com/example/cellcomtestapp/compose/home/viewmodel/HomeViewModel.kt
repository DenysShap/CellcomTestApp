package com.example.cellcomtestapp.compose.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cellcomtestapp.compose.home.model.HomeEvent
import com.example.cellcomtestapp.compose.home.model.HomeState
import com.example.cellcomtestapp.compose.home.model.MoviesType
import com.example.domain.usecases.movie.GetNowPlayingMoviesPaginationUseCase
import com.example.domain.usecases.movie.GetPopularMoviesPaginationUseCase
import com.example.domain.usecases.movie.GetTopRatedMoviesPaginationUseCase
import com.example.domain.usecases.movie.GetUpcomingMoviesPaginationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesPaginationUseCase: GetPopularMoviesPaginationUseCase,
    private val getNowPlayingMoviesPaginationUseCase: GetNowPlayingMoviesPaginationUseCase,
    private val getTopRatedMoviesPaginationUseCase: GetTopRatedMoviesPaginationUseCase,
    private val getUpcomingMoviesPaginationUseCase: GetUpcomingMoviesPaginationUseCase,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        fetchMoviesByType(MoviesType.Popular)
    }

    private fun fetchMoviesByType(moviesType: MoviesType) {
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(isLoading = true)
            val pagingFlow = when (moviesType) {
                MoviesType.Popular -> getPopularMoviesPaginationUseCase()
                MoviesType.NowPlaying -> getNowPlayingMoviesPaginationUseCase()
                MoviesType.Upcoming -> getUpcomingMoviesPaginationUseCase()
                MoviesType.TopRated -> getTopRatedMoviesPaginationUseCase()
            }

            _homeState.value = homeState.value.copy(
                isLoading = false,
                movieItemsFlow = pagingFlow
            )
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SelectCategory -> onChipSelected(event.movieType)
        }
    }

    private fun onChipSelected(selectedType: MoviesType) {
        val updatedChips = _homeState.value.chipsUiItem.map { chip ->
            chip.copy(isSelected = chip.chipType == selectedType)
        }

        _homeState.value = _homeState.value.copy(
            chipsUiItem = updatedChips,
            moviesType = selectedType,
        )
        fetchMoviesByType(selectedType)
    }
}
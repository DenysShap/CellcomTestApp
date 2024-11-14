package com.example.cellcomtestapp.compose.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cellcomtestapp.compose.detail.mapper.MovieDetailsMapper.toFavouriteItem
import com.example.cellcomtestapp.compose.detail.model.MovieDetailsEvent
import com.example.cellcomtestapp.compose.detail.model.MovieDetailsState
import com.example.cellcomtestapp.compose.detail.model.VideoTypes
import com.example.domain.model.details.Credits
import com.example.domain.model.details.MovieDetails
import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.model.trailer.TrailerVideo
import com.example.domain.usecases.favourite.AddFavouriteItemUseCase
import com.example.domain.usecases.favourite.CheckMovieIsFavouriteUseCase
import com.example.domain.usecases.favourite.RemoveFavouriteItemUseCase
import com.example.domain.usecases.movie.GetMovieCastDetailsUseCase
import com.example.domain.usecases.movie.GetMovieDetailsUseCase
import com.example.domain.usecases.movie.GetTrailerVideoUseCase
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCastDetailsUseCase: GetMovieCastDetailsUseCase,
    private val getTrailerVideoUseCase: GetTrailerVideoUseCase,
    private val checkMovieIsFavouriteUseCase: CheckMovieIsFavouriteUseCase,
    private val removeFavouriteItemUseCase: RemoveFavouriteItemUseCase,
    private val addFavouriteItemUseCase: AddFavouriteItemUseCase,
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private var isInitialized = false

    fun initialize(
        movieId: String,
        movieTitle: String,
    ) {
        if (isInitialized) return
        isInitialized = true
        _movieDetailsState.value = movieDetailsState.value.copy(titleName = movieTitle)

        getMovieDetails(movieId)
        getCastDetails(movieId)
        getTrailerVideo(movieId)
        checkIfIsFavoriteMovie(movieId)
    }


    private fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            _movieDetailsState.value = movieDetailsState.value.copy(isLoadingMovieDetails = true)
            getMovieDetailsUseCase(movieId).collect { movieDetailsResult ->
                when (movieDetailsResult) {
                    is Resource.Empty -> {}
                    is Resource.Error -> handleErrorMovieDetailsState()
                    is Resource.Loading -> handleMovieDetailsLoadingState()
                    is Resource.Success -> handleSuccessMovieDetailsState(movieDetailsResult)
                }
            }
        }
    }

    private fun getCastDetails(movieId: String) {
        viewModelScope.launch {
            _movieDetailsState.value = movieDetailsState.value.copy(isLoadingCasts = true)
            getMovieCastDetailsUseCase(movieId).collect { movieCastDetailsResult ->
                when (movieCastDetailsResult) {
                    is Resource.Empty -> {}
                    is Resource.Error -> handleErrorCastState()
                    is Resource.Loading -> handleCastDetailsLoadingState()
                    is Resource.Success -> handleSuccessCastDetailsState(movieCastDetailsResult)
                }
            }
        }
    }

    private fun getTrailerVideo(movieId: String) {
        viewModelScope.launch {
            getTrailerVideoUseCase(movieId).collect { trailerVideoResult ->
                when (trailerVideoResult) {
                    is Resource.Empty -> {}
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> handleSuccessTrailerVideoState(trailerVideoResult)
                }
            }
        }
    }

    private fun handleErrorCastState() {
        _movieDetailsState.value =
            movieDetailsState.value.copy(isLoadingCasts = false, isErrorCasts = true)
    }

    private fun handleErrorMovieDetailsState() {
        _movieDetailsState.value =
            movieDetailsState.value.copy(isLoadingMovieDetails = false, isErrorMovieDetails = true)
    }

    private fun handleMovieDetailsLoadingState() {
        _movieDetailsState.value =
            movieDetailsState.value.copy(isLoadingMovieDetails = true, isErrorMovieDetails = false)
    }

    private fun handleCastDetailsLoadingState() {
        _movieDetailsState.value =
            movieDetailsState.value.copy(isLoadingCasts = true, isErrorCasts = false)
    }

    private fun handleSuccessMovieDetailsState(movieDetailsResult: Resource.Success<MovieDetails>) {
        _movieDetailsState.value = movieDetailsState.value.copy(
            isLoadingMovieDetails = false,
            isErrorMovieDetails = false,
            movieDetails = movieDetailsResult.data
        )
    }

    private fun handleSuccessCastDetailsState(castDetailsResult: Resource.Success<Credits>) {
        _movieDetailsState.value = movieDetailsState.value.copy(
            isLoadingCasts = false,
            isErrorCasts = false,
            credits = castDetailsResult.data
        )
    }

    private fun handleSuccessTrailerVideoState(trailerVideoResult: Resource.Success<TrailerVideo>) {
        _movieDetailsState.value = movieDetailsState.value.copy(
            playerCode = trailerVideoResult.data?.results?.last { it.type == VideoTypes.Trailer.type }?.key
        )
    }

    fun onEvent(event: MovieDetailsEvent) {
        when (event) {
            MovieDetailsEvent.AddToFavorites -> {
                movieDetailsState.value.movieDetails?.toFavouriteItem(true)
                    ?.let {
                        addMovieToFavourite(it)
                        updateFavouriteStatus(true)
                    }
            }

            is MovieDetailsEvent.OpenTrailer -> {

            }

            MovieDetailsEvent.RemoveFromFavourite -> {
                movieDetailsState.value.movieDetails?.toFavouriteItem(true)
                    ?.let {
                        removeFavorite(it)
                        updateFavouriteStatus(false)
                    }
            }
        }
    }

    private fun updateFavouriteStatus(isFavourite: Boolean) {
        _movieDetailsState.value = movieDetailsState.value.copy(isFavourite = isFavourite)
    }

    private fun checkIfIsFavoriteMovie(movieId: String) {
        viewModelScope.launch {
            checkMovieIsFavouriteUseCase(movieId).collect { isFavourite ->
                updateFavouriteStatus(isFavourite)
            }
        }
    }

    private fun addMovieToFavourite(favorite: FavouriteItem) {
        viewModelScope.launch {
            addFavouriteItemUseCase(favorite)
        }
    }

    private fun removeFavorite(favorite: FavouriteItem) {
        viewModelScope.launch {
            removeFavouriteItemUseCase(favorite)
        }
    }
}
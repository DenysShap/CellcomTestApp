package com.example.cellcomtestapp.compose.detail.model

sealed class MovieDetailsEvent {
    data class OpenTrailer(val movieId: String) : MovieDetailsEvent()
    data object AddToFavorites : MovieDetailsEvent()
    data object RemoveFromFavourite : MovieDetailsEvent()
}
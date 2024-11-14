package com.example.cellcomtestapp.compose.detail.model

import com.example.domain.model.details.Credits
import com.example.domain.model.details.MovieDetails

data class MovieDetailsState(
    val titleName: String = "",
    val isFavourite: Boolean = false,
    val credits: Credits? = null,
    val isLoadingMovieDetails: Boolean = false,
    val isLoadingCasts: Boolean = false,
    val isErrorMovieDetails: Boolean = false,
    val isErrorCasts: Boolean = false,
    val movieDetails: MovieDetails? = null,
    val playerCode: String? = null,
)

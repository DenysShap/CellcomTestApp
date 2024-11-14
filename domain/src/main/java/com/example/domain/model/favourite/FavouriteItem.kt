package com.example.domain.model.favourite

data class FavouriteItem(
    val movieId: String,
    val isFavorite: Boolean,
    val backdropPath: String?,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)

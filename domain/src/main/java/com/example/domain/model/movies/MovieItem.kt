package com.example.domain.model.movies


data class MovieItem(
    val backdropPath: String?,
    val posterPath: String?,
    val id: Int,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)
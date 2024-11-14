package com.example.domain.model.movies

data class TopRatedMovie(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItem>,
    val totalResults: Int,
)
package com.example.domain.model.movies

data class NowPlayingMovie(
    val dates: Dates,
    val page: Int,
    val totalPages: Int,
    val results: List<MovieItem>,
    val totalResults: Int,
)
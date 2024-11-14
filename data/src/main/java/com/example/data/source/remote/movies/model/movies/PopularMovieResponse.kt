package com.example.data.source.remote.movies.model.movies

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
)

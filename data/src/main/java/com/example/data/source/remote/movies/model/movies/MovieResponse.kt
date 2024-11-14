package com.example.data.source.remote.movies.model.movies

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean = true,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean = true,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
)
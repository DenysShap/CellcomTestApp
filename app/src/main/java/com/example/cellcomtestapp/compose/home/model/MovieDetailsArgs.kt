package com.example.cellcomtestapp.compose.home.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsArgs(
    val movieId: String,
    val movieTitle: String,
)
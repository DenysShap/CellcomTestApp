package com.example.cellcomtestapp.compose.navigation.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route: String) {
    @Serializable
    data object Home :
        Screens("com.example.cellcomtestapp.compose.navigation.model.Screens.Home")

    @Serializable
    data object Favourites :
        Screens("com.example.cellcomtestapp.compose.navigation.model.Screens.Favourites")

    @Serializable
    data class MovieDetailsScreen(
        val movieId: String,
        val movieTitle: String,
    ) : Screens("com.example.cellcomtestapp.compose.navigation.model.Screens.MovieDetailsScreen")

    @Serializable
    data class YoutubePlayerScreen(val youtubeCode: String) :
        Screens("com.example.cellcomtestapp.compose.navigation.model.Screens.YoutubePlayerScreen")
}
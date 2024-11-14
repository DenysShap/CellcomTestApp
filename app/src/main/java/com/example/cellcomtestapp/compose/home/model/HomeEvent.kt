package com.example.cellcomtestapp.compose.home.model

sealed class HomeEvent {
    data class SelectCategory(val movieType: MoviesType) : HomeEvent()
}
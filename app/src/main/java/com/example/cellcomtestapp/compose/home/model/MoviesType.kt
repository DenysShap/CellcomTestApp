package com.example.cellcomtestapp.compose.home.model

import androidx.annotation.StringRes
import com.example.cellcomtestapp.R

sealed class MoviesType(@StringRes val resId: Int) {
    data object NowPlaying : MoviesType(R.string.now_playing)
    data object Popular : MoviesType(R.string.popular)
    data object Upcoming : MoviesType(R.string.upcoming)
    data object TopRated : MoviesType(R.string.top_rated)
}
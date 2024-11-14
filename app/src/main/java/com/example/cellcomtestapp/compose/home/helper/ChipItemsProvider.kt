package com.example.cellcomtestapp.compose.home.helper

import com.example.cellcomtestapp.compose.home.model.ChipUiItem
import com.example.cellcomtestapp.compose.home.model.MoviesType

fun provideChipUiItems(): List<ChipUiItem> = listOf(
    ChipUiItem(isSelected = true, chipType = MoviesType.Popular),
    ChipUiItem(isSelected = false, chipType = MoviesType.TopRated),
    ChipUiItem(isSelected = false, chipType = MoviesType.NowPlaying),
    ChipUiItem(isSelected = false, chipType = MoviesType.Upcoming),
)
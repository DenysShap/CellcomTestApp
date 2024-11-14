package com.example.cellcomtestapp.compose.favourite.model

import com.example.domain.model.favourite.FavouriteItem

data class FavouriteMoviesState(
    val favouriteItems: List<FavouriteItem> = emptyList(),
    val isEmpty: Boolean = false,
    val isShowRemoveDialog: Boolean = false,
)
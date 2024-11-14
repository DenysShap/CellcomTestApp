package com.example.cellcomtestapp.compose.favourite.model

sealed class FavouriteEvent {
    data object RemoveAllFavouritesMovie : FavouriteEvent()
    data object CancelRemoveDialog : FavouriteEvent()
    data object ShowRemoveDialog : FavouriteEvent()
}
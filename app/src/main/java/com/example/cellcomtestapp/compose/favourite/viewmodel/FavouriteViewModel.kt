package com.example.cellcomtestapp.compose.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cellcomtestapp.compose.favourite.model.FavouriteEvent
import com.example.cellcomtestapp.compose.favourite.model.FavouriteMoviesState
import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.usecases.favourite.GetFavouritesItemsUseCase
import com.example.domain.usecases.favourite.RemoveAllFavouritesItemsUseCase
import com.example.domain.usecases.favourite.RemoveFavouriteItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val removeFavouriteItemUseCase: RemoveFavouriteItemUseCase,
    private val removeAllFavouritesItemsUseCase: RemoveAllFavouritesItemsUseCase,
    private val getFavouritesItemsUseCase: GetFavouritesItemsUseCase,
) : ViewModel() {
    private val _favouriteState = MutableStateFlow(FavouriteMoviesState())
    val favouriteState = _favouriteState.asStateFlow()

    init {
        loadFavouritesItems()
    }

    private fun loadFavouritesItems() {
        viewModelScope.launch {
            getFavouritesItemsUseCase().collect { favouriteItems ->
                _favouriteState.value = favouriteState.value.copy(
                    favouriteItems = favouriteItems,
                    isEmpty = favouriteItems.isEmpty()
                )
            }
        }
    }

    private fun removeFavouriteMovie(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            removeFavouriteItemUseCase(favouriteItem)
            loadFavouritesItems()
        }
    }

    private fun eraseFavouritesMovies() {
        viewModelScope.launch {
            removeAllFavouritesItemsUseCase()
            _favouriteState.value = favouriteState.value.copy(
                favouriteItems = emptyList(),
                isShowRemoveDialog = false,
                isEmpty = true
            )
        }
    }

    fun onEvent(event: FavouriteEvent) {
        when (event) {
            FavouriteEvent.RemoveAllFavouritesMovie -> eraseFavouritesMovies()
            FavouriteEvent.CancelRemoveDialog -> _favouriteState.value =
                favouriteState.value.copy(isShowRemoveDialog = false)

            FavouriteEvent.ShowRemoveDialog -> _favouriteState.value =
                favouriteState.value.copy(isShowRemoveDialog = true)
        }
    }
}
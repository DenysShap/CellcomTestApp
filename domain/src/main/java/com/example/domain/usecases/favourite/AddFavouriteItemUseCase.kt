package com.example.domain.usecases.favourite

import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class AddFavouriteItemUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(favouriteItem: FavouriteItem) {
        withContext(dispatcherProvider.io) {
            favouritesRepository.insertFavourite(favouriteItem)
        }
    }
}
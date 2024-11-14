package com.example.domain.usecases.favourite

import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class RemoveFavouriteItemUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(favourite: FavouriteItem) {
        withContext(dispatcherProvider.io) {
            favouritesRepository.deleteFavouriteItem(favourite)
        }
    }
}
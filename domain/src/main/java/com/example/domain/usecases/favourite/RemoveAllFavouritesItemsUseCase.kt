package com.example.domain.usecases.favourite

import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class RemoveAllFavouritesItemsUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke() {
        withContext(dispatcherProvider.io) {
            favouritesRepository.deleteAllFavourites()
        }
    }
}
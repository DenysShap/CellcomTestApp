package com.example.domain.usecases.favourite

import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetFavouritesItemsUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(): Flow<List<FavouriteItem>> =
        withContext(dispatcherProvider.io) {
            favouritesRepository.getFavourites()
        }

}
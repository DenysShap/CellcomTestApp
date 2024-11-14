package com.example.domain.usecases.favourite

import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetFavouriteItemUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(movieId: String): Flow<FavouriteItem?> =
        withContext(dispatcherProvider.io) {
            favouritesRepository.getFavouriteItem(movieId)
        }
}
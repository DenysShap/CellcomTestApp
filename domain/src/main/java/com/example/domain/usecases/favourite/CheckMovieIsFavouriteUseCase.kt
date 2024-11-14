package com.example.domain.usecases.favourite

import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CheckMovieIsFavouriteUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val favouritesRepository: FavouritesRepository
) {
    suspend operator fun invoke(movieId: String): Flow<Boolean> =
        withContext(dispatcherProvider.io) {
            favouritesRepository.isFavorite(movieId)
        }
}
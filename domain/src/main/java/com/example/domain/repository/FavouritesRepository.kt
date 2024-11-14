package com.example.domain.repository

import com.example.domain.model.favourite.FavouriteItem
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun insertFavourite(favorite: FavouriteItem)
    fun getFavourites(): Flow<List<FavouriteItem>>
    fun isFavorite(movieId: String): Flow<Boolean>
    fun getFavouriteItem(movieId: String): Flow<FavouriteItem?>
    suspend fun deleteFavouriteItem(favourite: FavouriteItem)
    suspend fun deleteAllFavourites()
}
package com.example.data.repository

import com.example.data.mapper.FavouritesMapper.toFavourite
import com.example.data.mapper.FavouritesMapper.toFavouriteItem
import com.example.data.source.local.movies.database.FavouriteDatabase
import com.example.domain.model.favourite.FavouriteItem
import com.example.domain.repository.FavouritesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouritesRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val database: FavouriteDatabase
) : FavouritesRepository {
    override suspend fun insertFavourite(favorite: FavouriteItem) {
        database.dao.insertFavourite(favorite.toFavourite())
    }

    override fun getFavourites(): Flow<List<FavouriteItem>> {
        return database.dao.getAllFavourites().map { favouriteList ->
            favouriteList.map { it.toFavouriteItem() }
        }
    }

    override fun isFavorite(movieId: String): Flow<Boolean> {
        return database.dao.isFavourite(movieId)
    }

    override fun getFavouriteItem(movieId: String): Flow<FavouriteItem?> {
        return database.dao.getFavourites(movieId).map { favourite ->
            favourite?.toFavouriteItem()
        }
    }

    override suspend fun deleteFavouriteItem(favourite: FavouriteItem) {
        database.dao.deleteFavourite(favourite.toFavourite())
    }

    override suspend fun deleteAllFavourites() {
        database.dao.deleteAllFavourites()
    }
}
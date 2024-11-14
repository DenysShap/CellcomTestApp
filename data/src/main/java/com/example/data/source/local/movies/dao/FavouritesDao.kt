package com.example.data.source.local.movies.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.source.local.movies.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Insert
    suspend fun insertFavourite(favorite: Favourite)

    @Query("SELECT * FROM favorites_table ORDER BY movieId DESC")
    fun getAllFavourites(): Flow<List<Favourite>>

    @Query("SELECT * FROM favorites_table WHERE movieId  == :movieId")
    fun getFavourites(movieId: String): Flow<Favourite?>

    @Query("SELECT isFavorite FROM favorites_table WHERE movieId = :movieId")
    fun isFavourite(movieId: String): Flow<Boolean>

    @Delete
    suspend fun deleteFavourite(favorite: Favourite)

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAllFavourites()
}

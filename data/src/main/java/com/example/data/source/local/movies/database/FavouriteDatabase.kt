package com.example.data.source.local.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.source.local.movies.dao.FavouritesDao
import com.example.data.source.local.movies.model.Favourite

private const val VERSION_ONE = 1

@Database(entities = [Favourite::class], version = VERSION_ONE, exportSchema = true)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract val dao: FavouritesDao
}
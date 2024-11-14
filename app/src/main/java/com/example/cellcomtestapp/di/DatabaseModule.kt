package com.example.cellcomtestapp.di

import android.app.Application
import androidx.room.Room
import com.example.data.source.local.movies.database.FavouriteDatabase
import com.example.data.util.Constants.FAVOURITE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFavoritesDatabase(application: Application): FavouriteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavouriteDatabase::class.java,
            FAVOURITE_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
package com.example.cellcomtestapp.di

import com.example.data.repository.FavouritesRepositoryImpl
import com.example.data.repository.MoviesRepositoryImpl
import com.example.data.source.local.movies.database.FavouriteDatabase
import com.example.data.source.remote.movies.api.MovieApi
import com.example.domain.repository.FavouritesRepository
import com.example.domain.repository.MoviesRepository
import com.example.domain.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideMoviesRepository(
        dispatcherProvider: DispatcherProvider,
        movieApi: MovieApi,
    ): MoviesRepository = MoviesRepositoryImpl(
        dispatcherProvider = dispatcherProvider,
        movieApi = movieApi,
    )

    @Provides
    fun provideFavouritesRepository(
        dispatcherProvider: DispatcherProvider,
        database: FavouriteDatabase,
    ): FavouritesRepository = FavouritesRepositoryImpl(
        dispatcherProvider = dispatcherProvider,
        database = database,
    )
}
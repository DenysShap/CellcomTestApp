package com.example.cellcomtestapp.di

import com.example.domain.repository.FavouritesRepository
import com.example.domain.repository.MoviesRepository
import com.example.domain.usecases.favourite.AddFavouriteItemUseCase
import com.example.domain.usecases.favourite.CheckMovieIsFavouriteUseCase
import com.example.domain.usecases.favourite.GetFavouriteItemUseCase
import com.example.domain.usecases.favourite.GetFavouritesItemsUseCase
import com.example.domain.usecases.favourite.RemoveAllFavouritesItemsUseCase
import com.example.domain.usecases.favourite.RemoveFavouriteItemUseCase
import com.example.domain.usecases.movie.GetMovieCastDetailsUseCase
import com.example.domain.usecases.movie.GetMovieDetailsUseCase
import com.example.domain.usecases.movie.GetNowPlayingMoviesPaginationUseCase
import com.example.domain.usecases.movie.GetPopularMoviesPaginationUseCase
import com.example.domain.usecases.movie.GetTopRatedMoviesPaginationUseCase
import com.example.domain.usecases.movie.GetTrailerVideoUseCase
import com.example.domain.usecases.movie.GetUpcomingMoviesPaginationUseCase
import com.example.domain.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetPopularMoviesPaginationUseCase(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetPopularMoviesPaginationUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideGetNowPlayingMoviesPaginationUseCase(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetNowPlayingMoviesPaginationUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideGetTopRatedMoviesPaginationUseCase(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetTopRatedMoviesPaginationUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideGetUpcomingMoviesPaginationUseCase(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetUpcomingMoviesPaginationUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideGetMovieDetailsUseCase(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetMovieDetailsUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideGetMovieCastDetails(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetMovieCastDetailsUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideGetTrailerVideoUseCase(
        dispatcherProvider: DispatcherProvider,
        moviesRepository: MoviesRepository,
    ) = GetTrailerVideoUseCase(
        dispatcherProvider = dispatcherProvider,
        moviesRepository = moviesRepository,
    )

    @Provides
    fun provideAddFavouriteItemUseCase(
        dispatcherProvider: DispatcherProvider,
        favouritesRepository: FavouritesRepository,
    ) = AddFavouriteItemUseCase(
        dispatcherProvider = dispatcherProvider,
        favouritesRepository = favouritesRepository,
    )

    @Provides
    fun provideCheckItemIsFavouriteUseCase(
        dispatcherProvider: DispatcherProvider,
        favouritesRepository: FavouritesRepository,
    ) = CheckMovieIsFavouriteUseCase(
        dispatcherProvider = dispatcherProvider,
        favouritesRepository = favouritesRepository,
    )

    @Provides
    fun provideGetFavouriteItemUseCase(
        dispatcherProvider: DispatcherProvider,
        favouritesRepository: FavouritesRepository,
    ) = GetFavouriteItemUseCase(
        dispatcherProvider = dispatcherProvider,
        favouritesRepository = favouritesRepository,
    )

    @Provides
    fun provideGetFavouritesItemsUseCase(
        dispatcherProvider: DispatcherProvider,
        favouritesRepository: FavouritesRepository,
    ) = GetFavouritesItemsUseCase(
        dispatcherProvider = dispatcherProvider,
        favouritesRepository = favouritesRepository,
    )

    @Provides
    fun provideRemoveAllFavouritesItemsUseCase(
        dispatcherProvider: DispatcherProvider,
        favouritesRepository: FavouritesRepository,
    ) = RemoveAllFavouritesItemsUseCase(
        dispatcherProvider = dispatcherProvider,
        favouritesRepository = favouritesRepository,
    )

    @Provides
    fun provideRemoveFavouriteItemUseCase(
        dispatcherProvider: DispatcherProvider,
        favouritesRepository: FavouritesRepository,
    ) = RemoveFavouriteItemUseCase(
        dispatcherProvider = dispatcherProvider,
        favouritesRepository = favouritesRepository,
    )
}
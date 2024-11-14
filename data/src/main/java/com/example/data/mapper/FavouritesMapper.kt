package com.example.data.mapper

import com.example.data.source.local.movies.model.Favourite
import com.example.domain.model.favourite.FavouriteItem

object FavouritesMapper {
    fun Favourite.toFavouriteItem(): FavouriteItem =
        FavouriteItem(
            movieId = movieId,
            isFavorite = isFavorite,
            backdropPath = backdropPath,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage,
        )

    fun FavouriteItem.toFavourite(): Favourite =
        Favourite(
            movieId = movieId,
            isFavorite = isFavorite,
            backdropPath = backdropPath,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage,
        )
}
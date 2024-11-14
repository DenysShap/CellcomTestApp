package com.example.cellcomtestapp.compose.detail.mapper

import com.example.data.util.Constants.EMPTY_STRING
import com.example.domain.model.details.MovieDetails
import com.example.domain.model.favourite.FavouriteItem

object MovieDetailsMapper {
    fun MovieDetails.toFavouriteItem(isFavourite: Boolean): FavouriteItem =
        FavouriteItem(
            movieId = id.toString(),
            isFavorite = isFavourite,
            backdropPath = backdropPath,
            posterPath = posterPath,
            releaseDate = releaseDate ?: EMPTY_STRING,
            title = title,
            voteAverage = voteAverage,
        )
}
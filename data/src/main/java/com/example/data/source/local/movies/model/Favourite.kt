package com.example.data.source.local.movies.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.util.Constants.FAVOURITE_TABLE_NAME

@Entity(tableName = FAVOURITE_TABLE_NAME)
data class Favourite(
    @PrimaryKey val movieId: String,
    val isFavorite: Boolean,
    val backdropPath: String?,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)

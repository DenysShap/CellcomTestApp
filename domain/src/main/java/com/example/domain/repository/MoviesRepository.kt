package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.details.Credits
import com.example.domain.model.details.MovieDetails
import com.example.domain.model.movies.MovieItem
import com.example.domain.model.trailer.TrailerVideo
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopularMoviesPagination(): Flow<PagingData<MovieItem>>
    suspend fun getNowPlayingMoviesPagination(): Flow<PagingData<MovieItem>>
    suspend fun getUpcomingMoviesPagination(): Flow<PagingData<MovieItem>>
    suspend fun getTopRatedMoviesPagination(): Flow<PagingData<MovieItem>>

    suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>>
    suspend fun getMovieCastsDetails(movieId: String): Flow<Resource<Credits>>
    suspend fun getTrailerVideo(movieId: String): Flow<Resource<TrailerVideo>>
}
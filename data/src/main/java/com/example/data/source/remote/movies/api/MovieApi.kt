package com.example.data.source.remote.movies.api

import com.example.data.source.remote.movies.model.details.CreditsResponse
import com.example.data.source.remote.movies.model.details.MovieDetailsResponse
import com.example.data.source.remote.movies.model.movies.NowPlayingMovieResponse
import com.example.data.source.remote.movies.model.movies.PopularMovieResponse
import com.example.data.source.remote.movies.model.movies.TopRatedMoviesResponse
import com.example.data.source.remote.movies.model.movies.UpcomingMovieResponse
import com.example.data.source.remote.movies.model.trailer.TrailerVideoResponse
import com.example.data.util.Constants.EN_US_LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("language") language: String = EN_US_LANGUAGE,
    ): Response<NowPlayingMovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = EN_US_LANGUAGE,
    ): Response<PopularMovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("language") language: String = EN_US_LANGUAGE,
    ): Response<UpcomingMovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
    ): Response<TopRatedMoviesResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String = EN_US_LANGUAGE,
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = EN_US_LANGUAGE,
    ): Response<CreditsResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getTrailerVideo(
        @Path("movieId") movieId: String,
        @Query("language") language: String = EN_US_LANGUAGE,
    ): Response<TrailerVideoResponse>
}
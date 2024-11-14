package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.mapper.MoviesMapper.toCredits
import com.example.data.mapper.MoviesMapper.toMovieDetails
import com.example.data.mapper.MoviesMapper.toTrailerVideo
import com.example.data.paging.NowPlayingPagingSource
import com.example.data.paging.PopularPagingSource
import com.example.data.paging.TopRatedPagingSource
import com.example.data.paging.UpcomingPagingSource
import com.example.data.source.remote.movies.api.MovieApi
import com.example.data.util.Constants.TOTAL_PAGES
import com.example.data.util.safeApiCall
import com.example.domain.model.details.Credits
import com.example.domain.model.details.MovieDetails
import com.example.domain.model.movies.MovieItem
import com.example.domain.model.trailer.TrailerVideo
import com.example.domain.repository.MoviesRepository
import com.example.domain.utils.DispatcherProvider
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val movieApi: MovieApi
) : MoviesRepository {

    override suspend fun getPopularMoviesPagination(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = TOTAL_PAGES,
        ),
        pagingSourceFactory = { PopularPagingSource(movieApi) }
    ).flow

    override suspend fun getNowPlayingMoviesPagination(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = TOTAL_PAGES,
        ),
        pagingSourceFactory = { NowPlayingPagingSource(movieApi) }
    ).flow

    override suspend fun getUpcomingMoviesPagination(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = TOTAL_PAGES,
        ),
        pagingSourceFactory = { UpcomingPagingSource(movieApi) }
    ).flow

    override suspend fun getTopRatedMoviesPagination(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = TOTAL_PAGES,
        ),
        pagingSourceFactory = { TopRatedPagingSource(movieApi) }
    ).flow

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> {
        return flow {
            emit(Resource.Loading(true))
            val movieDetails = safeApiCall(
                apiCall = { movieApi.getMovieDetails(movieId = movieId) },
                map = { apiResponse -> apiResponse.toMovieDetails() }
            )
            emit(movieDetails)
        }
    }

    override suspend fun getMovieCastsDetails(movieId: String): Flow<Resource<Credits>> {
        return flow {
            emit(Resource.Loading(true))
            val castDetails = safeApiCall(
                apiCall = { movieApi.getMovieCredits(movieId = movieId) },
                map = { apiResponse -> apiResponse.toCredits() }
            )
            emit(castDetails)
        }
    }

    override suspend fun getTrailerVideo(movieId: String): Flow<Resource<TrailerVideo>> {
        return flow {
            emit(Resource.Loading(true))
            val trailerVideo = safeApiCall(
                apiCall = { movieApi.getTrailerVideo(movieId = movieId) },
                map = { apiResponse -> apiResponse.toTrailerVideo() }
            )
            emit(trailerVideo)
        }
    }
}
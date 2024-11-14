package com.example.domain.usecases.movie

import androidx.paging.PagingData
import com.example.domain.model.movies.MovieItem
import com.example.domain.repository.MoviesRepository
import com.example.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetNowPlayingMoviesPaginationUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke(): Flow<PagingData<MovieItem>> =
        withContext(dispatcherProvider.io) {
            moviesRepository.getNowPlayingMoviesPagination()
        }
}
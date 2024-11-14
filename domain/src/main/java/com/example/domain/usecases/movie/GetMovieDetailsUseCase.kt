package com.example.domain.usecases.movie

import com.example.domain.model.details.MovieDetails
import com.example.domain.repository.MoviesRepository
import com.example.domain.utils.DispatcherProvider
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetMovieDetailsUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(movieId: String): Flow<Resource<MovieDetails>> =
        withContext(dispatcherProvider.io) {
            moviesRepository.getMovieDetails(movieId)
        }
}
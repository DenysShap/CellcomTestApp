package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.MoviesMapper.toUpcomingMovieList
import com.example.data.source.remote.movies.api.MovieApi
import com.example.data.util.Constants.ONE
import com.example.data.util.safeApiCall
import com.example.domain.model.movies.MovieItem
import retrofit2.HttpException
import java.io.IOException

class UpcomingPagingSource(private val movieApi: MovieApi) :
    PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val page = params.key ?: ONE
        return try {
            val popularMovie = safeApiCall(
                apiCall = { movieApi.getUpcomingMovies(page) },
                map = { apiResponse -> apiResponse.toUpcomingMovieList() }
            )
            val prevKey = if (page == ONE) null else page.minus(ONE)
            val nextKey = if (popularMovie.data?.isEmpty() == true) {
                null
            } else {
                page.plus(ONE)
            }
            LoadResult.Page(
                data = popularMovie.data!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE)
        }
    }
}
package com.example.data.util

import com.example.domain.utils.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

inline fun <T, R> safeApiCall(
    apiCall: () -> Response<T>,
    map: (T) -> R,
): Resource<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                Resource.Success(map(it))
            } ?: Resource.Empty()
        } else {
            Resource.Error()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Resource.Error(message = e.localizedMessage)
    } catch (e: HttpException) {
        e.printStackTrace()
        Resource.Error(message = e.localizedMessage, code = e.code())
    }
}
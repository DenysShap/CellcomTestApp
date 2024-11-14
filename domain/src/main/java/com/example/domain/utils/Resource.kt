package com.example.domain.utils

sealed class Resource<out T>(val data: T? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error(val message: String? = null, val code: Int? = null) : Resource<Nothing>()
    class Empty<T> : Resource<T>()
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}
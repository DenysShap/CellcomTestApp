package com.example.data.source.remote.movies.model.details

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)

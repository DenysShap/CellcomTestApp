package com.example.data.source.remote.movies.model.movies

import com.google.gson.annotations.SerializedName

data class DatesResponse(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String,
)
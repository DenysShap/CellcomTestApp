package com.example.data.source.remote.movies.model.details

import com.google.gson.annotations.SerializedName

data class ProductionCountriesResponse(
    @SerializedName("iso_3166_1")
    val iso: String,
    @SerializedName("name")
    val name: String,
)

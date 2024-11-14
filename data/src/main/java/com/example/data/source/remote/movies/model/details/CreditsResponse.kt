package com.example.data.source.remote.movies.model.details

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<CastResponse>,
    @SerializedName("id")
    val id: Int
)

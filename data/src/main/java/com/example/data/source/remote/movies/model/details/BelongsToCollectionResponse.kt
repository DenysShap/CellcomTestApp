package com.example.data.source.remote.movies.model.details

import com.google.gson.annotations.SerializedName


data class BelongsToCollectionResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster_path")
    val posterPath: String?
)

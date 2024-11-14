package com.example.data.source.remote.movies.model.trailer

import com.google.gson.annotations.SerializedName

data class TrailerVideoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<ResultsItemResponse>,
)

data class ResultsItemResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("published_at")
    val publishedAt: String,
)
package com.example.domain.model.trailer

data class TrailerVideo(
    val id: Int,
    val results: List<ResultsItem>,
)

data class ResultsItem(
    val id: String,
    val iso6391: String,
    val iso31661: String,
    val site: String,
    val size: Int,
    val name: String,
    val official: Boolean,
    val type: String,
    val publishedAt: String,
    val key: String
)
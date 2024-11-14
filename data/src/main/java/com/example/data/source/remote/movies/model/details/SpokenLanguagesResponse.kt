package com.example.data.source.remote.movies.model.details

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesResponse(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso: String,
    @SerializedName("name")
    val name: String,
)
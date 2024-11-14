package com.example.cellcomtestapp.compose.detail.model

sealed class VideoTypes(val type: String) {
    data object Trailer : VideoTypes("Trailer")
}
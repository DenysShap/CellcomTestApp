package com.example.cellcomtestapp.compose.navigation.model

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val label: String,
    val correspondingScreen: Screens,
    @DrawableRes
    val icon: Int,
    val route: String,
)

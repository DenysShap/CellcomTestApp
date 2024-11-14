package com.example.cellcomtestapp.compose.navigation.provider

import com.example.cellcomtestapp.R
import com.example.cellcomtestapp.compose.navigation.model.BottomNavItem
import com.example.cellcomtestapp.compose.navigation.model.Screens

fun provideBottomNavigationItems(): List<BottomNavItem> =
    listOf(
        BottomNavItem(
            label = "Home",
            correspondingScreen = Screens.Home,
            icon = R.drawable.ic_home,
            route = Screens.Home.route,
        ),
        BottomNavItem(
            label = "Favourites",
            correspondingScreen = Screens.Favourites,
            icon = R.drawable.ic_favourite_marked,
            route = Screens.Favourites.route,
        ),
    )
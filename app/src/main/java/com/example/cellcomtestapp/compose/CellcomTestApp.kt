package com.example.cellcomtestapp.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.cellcomtestapp.compose.navigation.BottomNavigationBar

@Composable
fun CellcomTestApp() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}
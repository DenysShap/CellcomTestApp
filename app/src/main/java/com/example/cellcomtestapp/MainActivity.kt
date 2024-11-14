package com.example.cellcomtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cellcomtestapp.compose.CellcomTestApp
import com.example.cellcomtestapp.compose.theme.CellcomTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellcomTestAppTheme(darkTheme = false, dynamicColor = false) {
                CellcomTestApp()
            }
        }
    }
}
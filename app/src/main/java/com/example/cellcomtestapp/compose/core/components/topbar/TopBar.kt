package com.example.cellcomtestapp.compose.core.components.topbar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.cellcomtestapp.compose.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Purple40)
    )
}
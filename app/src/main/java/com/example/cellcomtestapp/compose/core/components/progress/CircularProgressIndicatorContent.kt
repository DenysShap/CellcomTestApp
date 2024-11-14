package com.example.cellcomtestapp.compose.core.components.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cellcomtestapp.compose.theme.Purple40
import com.example.cellcomtestapp.compose.theme.Purple80
import com.example.cellcomtestapp.compose.theme.dp48

@Composable
fun CircularProgressIndicatorContent(modifier: Modifier = Modifier, isLoading: Boolean) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize(),
    ) {
        if (isLoading)
            CircularProgressIndicator(
                modifier = Modifier.width(dp48),
                color = Purple40,
                trackColor = Purple80,
            )
    }
}
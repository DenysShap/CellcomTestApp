package com.example.cellcomtestapp.compose.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.cellcomtestapp.compose.core.constants.Constants.PERCENT_SIGN
import com.example.cellcomtestapp.compose.theme.Purple40
import com.example.cellcomtestapp.compose.theme.dp40
import com.example.cellcomtestapp.compose.theme.dp48
import com.example.cellcomtestapp.compose.theme.sp12

@Composable
fun AverageVoteRatingIndicator(progressValue: Float) {
    Box(
        modifier = Modifier
            .width(dp48)
            .height(dp48)
            .clip(shape = CircleShape)
            .background(color = Purple40)
    ) {
        CircularProgressIndicator(
            progress = { progressValue },
            modifier = Modifier
                .align(Alignment.Center)
                .height(dp40)
                .width(dp40),
            color = Color.Green,
        )
        val percentage = (progressValue * 100).toInt()
        Text(
            text = buildAnnotatedString {
                append("$percentage")
                withStyle(style = SpanStyle(fontSize = sp12)) {
                    append(PERCENT_SIGN)
                }
            },
            color = Color.White,
            fontSize = sp12,
            maxLines = 1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
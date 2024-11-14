package com.example.cellcomtestapp.compose.core.components.empty

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cellcomtestapp.R
import com.example.cellcomtestapp.compose.theme.dp150
import com.example.cellcomtestapp.compose.theme.dp16

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    @StringRes
    emptyMessageResId: Int = R.string.empty_text,
    @DrawableRes
    emptyIcon: Int = R.drawable.ic_empty
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = emptyIcon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(dp150)
        )
        Spacer(modifier = Modifier.size(dp16))
        Text(
            text = stringResource(id = emptyMessageResId),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = dp16)
        )
    }
}
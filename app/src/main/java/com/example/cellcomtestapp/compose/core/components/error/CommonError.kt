package com.example.cellcomtestapp.compose.core.components.error

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
import com.example.cellcomtestapp.compose.theme.dp16

@Composable
fun CommonError(
    modifier: Modifier = Modifier,
    @StringRes
    errorMessageResId: Int = R.string.something_went_wrong,
    @DrawableRes
    errorImageResId: Int = R.drawable.ic_error
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = errorImageResId),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(dp16))
        Text(
            text = stringResource(id = errorMessageResId),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = dp16)
        )
    }
}
package com.example.cellcomtestapp.compose.core.components.wrapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cellcomtestapp.compose.core.components.empty.EmptyScreen
import com.example.cellcomtestapp.compose.core.components.error.CommonError

@Composable
fun ContentStateWrapper(
    modifier: Modifier,
    isLoading: Boolean,
    isError: Boolean,
    isEmpty: Boolean,
    loadingContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    when {
        isLoading -> loadingContent()
        isError -> CommonError(modifier)
        isEmpty -> EmptyScreen(modifier)
        else -> content()
    }
}
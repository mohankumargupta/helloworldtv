package com.melbpc.mohankumargupta.helloworldtv

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
    val nextBinDrawable by viewModel.nextBin.collectAsStateWithLifecycle()

    LaunchedEffect(nextBinDrawable) {
        viewModel.whichBin()
    }

    HomeComposable(nextBinDrawable, nextScreen)

}

@Composable
fun HomeComposable(
    @DrawableRes bin: Int?,
    nextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(bin) {
        if (bin != null)
        focusRequester.requestFocus()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bin != null) {
            item {
                Box(
                    modifier = modifier
                        .clickable {
                            nextScreen()
                        }
                        .focusRequester(focusRequester)
                        .focusable(),

                    ) {
                    Image(
                        painter = painterResource(bin),
                        contentDescription = "Collection Bin" // Provide meaningful content description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    HomeComposable(R.drawable.garden_bin_red, {}, modifier)
}
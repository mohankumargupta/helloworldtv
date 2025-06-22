package com.melbpc.mohankumargupta.helloworldtv.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel

@Composable
fun RecyclingBinColorScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        item {
            Text("Last recycling collection date: ${viewModel.recyclingReferenceDate.collectAsState().value} ")
        }
    }
}
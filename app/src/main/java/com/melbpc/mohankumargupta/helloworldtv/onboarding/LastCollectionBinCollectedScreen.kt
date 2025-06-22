package com.melbpc.mohankumargupta.helloworldtv.onboarding

import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.WideButton

@Composable
fun LastCollectionBinScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
    val instruction = """
        Need to know what bin was collected last ${viewModel.collectionDay.collectAsState().value}.
        
        
    """.trimIndent()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        item {
            Text("Last Collection Bin", modifier = Modifier
                .padding(vertical = 16.dp,
                    )
                , style = MaterialTheme.typography.headlineLarge
            )
            Text(instruction, modifier = Modifier.padding(bottom = 48.dp))
            LazyRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item {
                    WideButton(
                        onClick = {

                        }
                    ) {
                        Text("Recycling")
                    }
                }
                item {
                    WideButton(
                        onClick = {}
                    ) {
                        Text("Garden")
                    }
                }

            }
        }

    }

}
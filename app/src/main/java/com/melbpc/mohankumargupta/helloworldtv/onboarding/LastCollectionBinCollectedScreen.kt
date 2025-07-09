package com.melbpc.mohankumargupta.helloworldtv.onboarding

import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.windowInsetsEndWidth
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
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

enum class GarbageType {
    RECYCLING,
    GARDEN,
}

private fun getLastRecyclingCollectionDate(garbage: GarbageType, collectionDay: String): LocalDate {
    val today = LocalDate.now()
    val collectionDayOfWeek = DayOfWeek.valueOf(collectionDay.uppercase())
    val lastCollectionDayDate = today.with(TemporalAdjusters.previousOrSame(collectionDayOfWeek))
    return when (garbage) {
        // If recycling was the last thing collected, then the lastCollectionDayDate is the answer.
        GarbageType.RECYCLING -> lastCollectionDayDate
        // If garden waste was collected last, the recycling collection was the week before.
        GarbageType.GARDEN -> lastCollectionDayDate.minusWeeks(1)
    }
}

@Composable
fun LastCollectionBinScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
    val today = LocalDate.now()
    val currentDayOfWeek = today.dayOfWeek.name


    val collectionDay = viewModel.collectionDay.collectAsState().value
    val isCollectionDay = collectionDay.uppercase() == currentDayOfWeek
    val instructionLastWeek = """
        Need to know what bin was collected last ${collectionDay}.
    """.trimIndent()
    val instructionToday = """
        Need to know what bin was collected today
    """.trimIndent()
    val instruction = if (isCollectionDay) instructionToday else instructionLastWeek

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
        item {
            Text("Which Collection Bin?", modifier = Modifier
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
                            val lastRecycling = getLastRecyclingCollectionDate(GarbageType.RECYCLING, collectionDay)
                            viewModel.setRecyclingReferenceDate(lastRecycling)
                            nextScreen()
                        }
                    ) {
                        Text("Recycling")
                    }
                }
                item {
                    WideButton(
                        onClick = {
                            val lastRecycling = getLastRecyclingCollectionDate(GarbageType.GARDEN, collectionDay)
                            viewModel.setRecyclingReferenceDate(lastRecycling)
                            nextScreen()
                        }
                    ) {
                        Text("Garden")
                    }
                }

            }
        }

    }

}
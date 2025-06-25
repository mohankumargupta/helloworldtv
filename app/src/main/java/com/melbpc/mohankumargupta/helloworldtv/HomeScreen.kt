package com.melbpc.mohankumargupta.helloworldtv

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

//import androidx.tv.material3.Text

fun findNextDayOfWeek(targetDay: DayOfWeek, fromDate: LocalDate = LocalDate.now()): LocalDate {
    return fromDate.with(TemporalAdjusters.next(targetDay))
}

@Composable
fun HomeScreen(viewModel: MainViewModel) {

    val collectionDay = viewModel.collectionDay.collectAsState().value
    val recyclingBinColor = viewModel.recyclingBinColor.collectAsState().value
    val gardenBinColor = viewModel.gardenBinColor.collectAsState().value
    val recyclingReferenceDate = viewModel.recyclingReferenceDate.collectAsState().value

    val today = LocalDate.now()
    val isCollectionDayToday = collectionDay.uppercase() == today.dayOfWeek.name
//    Log.d("mohan", today.dayOfWeek.name)
//    Log.d("mohan", collectionDay.uppercase())
//    Log.d("mohan", isCollectionDayToday.toString())
    if (recyclingReferenceDate?.isBefore(today) == true) {
        var nextCollectionDate: LocalDate = if (isCollectionDayToday) today else findNextDayOfWeek(DayOfWeek.valueOf(collectionDay.uppercase()))
        Log.d("mohan", nextCollectionDate.toString())
    }




    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box {
//                Image(
//                    painter = painterResource(id = R.drawable.garden_bin_yellow),
//                    contentDescription = null
//                )
                Image(
                    painter = painterResource(id = R.drawable.garden_bin_black),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    //HomeScreen(viewModel)
}
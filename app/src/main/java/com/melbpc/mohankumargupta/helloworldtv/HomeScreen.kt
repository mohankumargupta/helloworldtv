package com.melbpc.mohankumargupta.helloworldtv

import android.util.Log
import androidx.annotation.DrawableRes
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
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

@Composable
fun HomeScreen(viewModel: MainViewModel) {

}



/*
@Composable
fun HomeScreen(viewModel: MainViewModel) {

    val collectionDay = viewModel.collectionDay.collectAsState().value
    val recyclingBinColor = viewModel.recyclingBinColor.collectAsState().value
    val gardenBinColor = viewModel.gardenBinColor.collectAsState().value
    val recyclingReferenceDate = viewModel.recyclingReferenceDate.collectAsState().value
    val lidColors = mapOf(
        Pair(BinType.GARDEN, ColorSwatch.Black) to R.drawable.garden_bin_black,
        Pair(BinType.GARDEN, ColorSwatch.DarkGreen) to R.drawable.garden_bin_darkgreen,
        Pair(BinType.GARDEN, ColorSwatch.Green) to R.drawable.garden_bin_green,
        Pair(BinType.GARDEN, ColorSwatch.Grey) to R.drawable.garden_bin_grey,
        Pair(BinType.GARDEN, ColorSwatch.Red) to R.drawable.garden_bin_red,
        Pair(BinType.GARDEN, ColorSwatch.Yellow) to R.drawable.garden_bin_yellow,
        Pair(BinType.GARDEN, ColorSwatch.Blue) to R.drawable.garden_bin_blue,
        Pair(BinType.GARDEN, ColorSwatch.Purple) to R.drawable.garden_bin_purple,

        Pair(BinType.RECYCLING, ColorSwatch.Black) to R.drawable.recycling_bin_black,
        Pair(BinType.RECYCLING, ColorSwatch.DarkGreen) to R.drawable.recycling_bin_darkgreen,
        Pair(BinType.RECYCLING, ColorSwatch.Green) to R.drawable.recycling_bin_green,
        Pair(BinType.RECYCLING, ColorSwatch.Grey) to R.drawable.recycling_bin_grey,
        Pair(BinType.RECYCLING, ColorSwatch.Red) to R.drawable.recycling_bin_red,
        Pair(BinType.RECYCLING, ColorSwatch.Yellow) to R.drawable.recycling_bin_yellow,
        Pair(BinType.RECYCLING, ColorSwatch.Blue) to R.drawable.recycling_bin_blue,
        Pair(BinType.RECYCLING, ColorSwatch.Purple) to R.drawable.recycling_bin_purple,
    )

    val today = LocalDate.now()
    val isCollectionDayToday = collectionDay.uppercase() == today.dayOfWeek.name
//    Log.d("mohan", today.dayOfWeek.name)
//    Log.d("mohan", collectionDay.uppercase())
//    Log.d("mohan", isCollectionDayToday.toString())
    if (recyclingReferenceDate?.isBefore(today) == true || recyclingReferenceDate?.isBefore(today) == false) {
        val nextCollectionDate: LocalDate =
            if (isCollectionDayToday) today else findNextDayOfWeek(DayOfWeek.valueOf(collectionDay.uppercase()))
        //Log.d("mohan", nextCollectionDate.toString())
        val weeks = ChronoUnit.WEEKS.between(recyclingReferenceDate, nextCollectionDate)
        //Log.d("mohan", weeks.toString())
        //val isRecycling = weeks % 2 == 0L

        //Log.d("mohan", isRecycling.toString())
        val nextBin = if (weeks % 2 == 0L) BinType.RECYCLING else BinType.GARDEN
        //Log.d("mohan", nextBin.toString())

        val lidColor = when (nextBin) {
            BinType.RECYCLING -> {
                lidColors[Pair(BinType.RECYCLING, recyclingBinColor)]
            }

            BinType.GARDEN -> {
                lidColors[Pair(BinType.GARDEN, gardenBinColor)]
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box {
                    if (lidColor != null) {
                        Image(
                            painter = painterResource(id = lidColor),
                            contentDescription = null
                        )

                    }
                }
            }
        }
    }
}
*/

@Composable
fun HomeComposable(
    @DrawableRes bin: Int?,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bin != null) {
            item {
                Box {
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
    HomeComposable(R.drawable.garden_bin_red, modifier)
}
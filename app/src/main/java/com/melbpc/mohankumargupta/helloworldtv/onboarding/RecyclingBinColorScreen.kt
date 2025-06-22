package com.melbpc.mohankumargupta.helloworldtv.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.tv.material3.MaterialTheme


@Composable
fun ColorChoice(
    colors: List<Color>,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedColor by remember { mutableStateOf(colors.first()) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        //modifier = modifier.padding(8.dp),
        //contentPadding = PaddingValues(8.dp),
        //horizontalArrangement = Arrangement.spacedBy(8.dp),
        //verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(colors) { color ->
            ColorDot(
                color = color,
                isSelected = color == selectedColor,
                onColorSelected = {
                    selectedColor = it
                    onColorSelected(it)
                }
            )
        }
    }
}

@Composable
private fun ColorDot(
    color: Color,
    isSelected: Boolean,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
//            .padding(8.dp)
//            .clip(CircleShape)
//            .background(Color.Transparent)
            .clickable { onColorSelected(color) }
    ) {
        Box {
            Box(modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    border = BorderStroke(width = 4.dp, color= Color.White)
                )
                .background(Color.Transparent)
                //.background(Color.DarkGray)
            )
            Box(modifier = Modifier
                .align(Alignment.Center)
                .size(20.dp)
                .clip(CircleShape)
                .background(color)
            )
        }
    }
}

//@Composable
//fun RecyclingBinColorScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
//    val sampleColors = listOf(
//        Color.Red,
//        Color.Green,
//        Color.Blue,
//        Color.Yellow,
//        Color.Cyan,
//        Color.Magenta,
//        Color.Gray,
//        Color.Black
//    )
//
//    // State for the selected color now lives here
//    var selectedColor by remember { mutableStateOf(sampleColors.first()) }
//
//    // Use LazyVerticalGrid as the main container for the whole screen
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2), // The grid has 2 columns
//        //modifier = Modifier.fillMaxSize(),
//        //contentPadding = PaddingValues(16.dp),
//        //horizontalArrangement = Arrangement.spacedBy(16.dp),
//        //verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        // 1. Add the Text as the first item
//        item(
//            // Make this item span across both columns
//            span = { GridItemSpan(2) }
//        ) {
//            Text(
//                text = "Pick color for Recycle Bin",
//                modifier = Modifier.padding(bottom = 16.dp),
//                style = MaterialTheme.typography.headlineLarge
//            )
//        }
//
//        // 2. Add the color dots directly into the grid
//        items(sampleColors, span = {
//            GridItemSpan(1)
//        }) { color ->
//            ColorDot(
//                color = color,
//                isSelected = color == selectedColor,
//                onColorSelected = { newColor ->
//                    selectedColor = newColor
//                    // Call your ViewModel or other logic here
//                    // viewModel.onColorSelected(newColor)
//                }
//            )
//        }
//    }
//}

@Composable
fun RecyclingBinColorScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
    val sampleColors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta,
        Color.Gray,
        Color.Black
    )

    var selectedColor by remember { mutableStateOf(sampleColors.first()) }

    // Split the list of 8 colors into a list of two lists, each containing 4 colors.
    val colorRows = sampleColors.chunked(4)

    // Use LazyColumn for the main vertical arrangement.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        // Center the rows horizontally within the column.
        horizontalAlignment = Alignment.CenterHorizontally,
        // Add vertical spacing between the header and the rows.
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Header Text
        item {
            Text(
                text = "Last recycling collection date: ${viewModel.recyclingReferenceDate.collectAsState().value}",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // 2. Create a LazyRow for each chunk of colors.
        // This will loop twice: once for the first 4 colors, once for the second 4.
        items(colorRows) { rowOfColors ->
            LazyRow (
                // Add horizontal spacing between the dots in a row.
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                // Ensure the row itself doesn't take up unnecessary space.
                modifier = Modifier.wrapContentSize()
            ) {
                items(rowOfColors) { color ->
                    ColorDot(
                        color = color,
                        isSelected = color == selectedColor,
                        onColorSelected = { newColor ->
                            selectedColor = newColor
                            // viewModel.onColorSelected(newColor)
                        }
                    )
                }
            }
        }
    }
}


//@Composable
//fun RecyclingBinColorScreen(viewModel: MainViewModel, nextScreen: () -> Unit) {
//    val sampleColors = listOf(
//        Color.Red,
//        Color.Green,
//        Color.Blue,
//        Color.Yellow,
//        Color.Cyan,
//        Color.Magenta,
//        Color.Gray,
//        Color.Black
//    )
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center)
//    ) {
//        item {
//            Text("Last recycling collection date: ${viewModel.recyclingReferenceDate.collectAsState().value} ")
//            ColorChoice(colors = sampleColors, onColorSelected = {})
//        }
//    }
//}

@Preview(backgroundColor = 0xFFA52A2A, showBackground = true)
@Composable
fun ColorChoicePreview() {
    val sampleColors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta,
        Color.Gray,
        Color.Black
    )
    ColorChoice(colors = sampleColors, onColorSelected = {})
}
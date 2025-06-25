package com.melbpc.mohankumargupta.helloworldtv.onboarding

//import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
//import androidx.tv.material3.ButtonBorder
import androidx.tv.material3.ButtonDefaults
//import androidx.tv.material3.ButtonShape
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.helloworldtv.BinType
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel

//fun Color.toHexString(): String {
//    return String.format("#%08X", this.toArgb())
//}

val colorSwatch = listOf(
    Color(0x00000000), // Black
    Color(0xFF1D6E2A), // Dark green
    Color(0xFF5FCC6A), // Green
    Color(0xFF8C8C8C), // Grey
    Color(0xFFF90101), // Red
    Color(0xFFFFE600), // Yellow
    Color(0xFF00A3FF), // Blue
    Color(0xFFB901F9), // Purple
)

@Composable
fun BinColorScreens(bin: BinType, viewModel: MainViewModel, nextScreen: () -> Unit) {
    var selectedColor by remember { mutableStateOf(colorSwatch.first()) }

    ColorPicker(
        bin,
        viewModel,
        nextScreen,
        colors = colorSwatch,
        selectedColor = selectedColor,
//        onColorSelected = { color: Color ->
//            selectedColor = color
//        }
    )
}


@Composable
fun ColorPicker(bin: BinType, viewModel: MainViewModel, nextScreen: () -> Unit, colors: List<Color>, selectedColor: Color) {
    // Split the list of 8 colors into two rows of 4
    val colorRows = colors.chunked(4)
    val binName = if (bin == BinType.RECYCLING) "Recycling" else "Garden"

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Pick color for $binName Bin",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 36.dp)
            )
        }

        items(colorRows) { rowColors ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between buttons in a row
            ) {
                items(rowColors) { color ->
                    ColorButton(
                        color = color,
                        isSelected = color == selectedColor,
                        onClick = {
                            when (bin) {
                                BinType.RECYCLING -> viewModel.setRecyclingBinColor(color)
                                BinType.GARDEN -> viewModel.setGardenBinColor(color)
                            }
                            nextScreen()
                            //onColorSelected(color)
                        }
                    )
                    //Text(color.red.toString())
                }

            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
        }
    }
}

@Composable
fun ColorButton(color: Color, isSelected: Boolean, onClick: () -> Unit) {
    var focus by remember { mutableStateOf(isSelected) }

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .size(50.dp)
            .onFocusChanged { focusState ->
                focus = focusState.isFocused
            }
        , // Define the size of the button

        shape = ButtonDefaults.shape(CircleShape),
        // The border provides a nice outline, especially for the white button
        border = ButtonDefaults.border(
            Border(
                BorderStroke(
                    width = if (focus) 3.dp else 1.dp,
                    color = if (focus) Color.White else Color.Black,
                )
            )
        ),
        
        colors = ButtonDefaults.colors(
            containerColor = color,
            focusedContainerColor = color
        ),
        scale = ButtonDefaults.scale(focusedScale = 1.2f),
        contentPadding = PaddingValues(0.dp), // Remove default padding
//        colors = ButtonDefaults.outlinedButtonColors(
//            containerColor = color // The button's background is the color itself
//        )
    ) {
        // The content is empty because the button's color is the visual representation.
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF)
@Composable
fun BinColorScreensPreview() {
    //BinColorScreens()
}
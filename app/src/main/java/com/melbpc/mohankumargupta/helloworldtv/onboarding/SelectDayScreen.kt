package com.melbpc.mohankumargupta.helloworldtv.onboarding

//import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import androidx.compose.ui.unit.dp

@Composable
fun SelectDayScreen() {

    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    LazyRow (
        modifier = Modifier
            .fillMaxSize()
    ) {
      item {
        LazyColumn(
            modifier = Modifier
                .fillParentMaxWidth(2f/3f)
                .fillMaxHeight()
        ) {
          item {
          Text("Pick a day")
          }
        }
      }

      item {
          LazyColumn(
              modifier = Modifier
              .fillParentMaxWidth(1f/3f)
              .fillMaxHeight(),
              verticalArrangement = Arrangement.spacedBy(16.dp)

          ) {
              items(days) { day ->
                  WideButton(onClick = {}) {
                      Text(day)
                  }
              }
          }
      }

      }

}
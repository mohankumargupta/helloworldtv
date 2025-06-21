package com.melbpc.mohankumargupta.helloworldtv.onboarding

//import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme

@Composable
fun SelectDayScreen( setCollectionDay: (String) -> Unit, modifier: Modifier = Modifier) {

    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val blurb = """
        This app will tell whether your next bin 
        collection is recycling or gardening.
               
        First, choose your collection day from the
        days of the week on the right.
    """.trimIndent()

    LazyRow (
        modifier = modifier.fillMaxSize()
    ) {
      item {
        LazyColumn(
            modifier = modifier
                .fillParentMaxWidth(2f/3f)
                .fillMaxHeight()
                .wrapContentSize(Alignment.Center)

        ) {
          item {

              Text("Recycling or gardening", style = MaterialTheme.typography.headlineLarge)
              Spacer(Modifier.size(32.dp))
              Text(blurb)
          //Text("Pick a day")
          }
        }
      }

      item {
          LazyColumn(
              modifier = Modifier
              .fillParentMaxWidth(1f/3f)
              .fillMaxHeight()
              .wrapContentSize(Alignment.Center)
              ,
              verticalArrangement = Arrangement.spacedBy(16.dp)

          ) {
              items(days) { day ->
                  WideButton(onClick = {
                      setCollectionDay(day)
                  }) {
                      Text(day)
                  }
              }
          }
      }

      }

}
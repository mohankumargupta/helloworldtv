package com.melbpc.mohankumargupta.helloworldtv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel
import com.melbpc.mohankumargupta.helloworldtv.onboarding.SelectDayScreen
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
private data object Home : NavKey

@Serializable
private data object OnboardingLastCollectionType : NavKey

@Serializable
private data object Settings: NavKey

@Composable
fun NavigationRoot(viewModel: MainViewModel) {
    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
          entry<Home> {
              SelectDayScreen(setCollectionDay = { day->
                  viewModel.setCollectionDay(day)
                  backStack.add(OnboardingLastCollectionType)
              })
          }
          entry<OnboardingLastCollectionType> {
              Text("Collection Day: ${viewModel.collectionDay.value}")
              //Text("Last collection type")
          }
          entry<Settings> {
              Text("settings")
          }
        }
    )
}
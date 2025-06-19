package com.melbpc.mohankumargupta.helloworldtv.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.tv.material3.Text
import kotlinx.serialization.Serializable

@Serializable
private data object Home : NavKey

@Serializable
private data object Settings: NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
          entry<Home> {
              Text("home")
          }
          entry<Settings> {
              Text("settings")
          }
        }
    )
}
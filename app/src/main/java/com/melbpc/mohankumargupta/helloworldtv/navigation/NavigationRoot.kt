package com.melbpc.mohankumargupta.helloworldtv.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.helloworldtv.BinType
import com.melbpc.mohankumargupta.helloworldtv.HomeScreen
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel
import com.melbpc.mohankumargupta.helloworldtv.onboarding.BinColorScreens
import com.melbpc.mohankumargupta.helloworldtv.onboarding.LastCollectionBinScreen
import com.melbpc.mohankumargupta.helloworldtv.onboarding.SelectDayScreen
//import com.melbpc.mohankumargupta.helloworldtv.onboarding.RecyclingBinColorScreen
import kotlinx.serialization.Serializable

@Serializable
private data object OnboardingHome : NavKey

@Serializable
private data object OnboardingLastCollectionType : NavKey

@Serializable
private data object Home : NavKey

@Serializable
private data object RecycleBinColorSelection : NavKey

@Serializable
private data object GardenBinColorSelection : NavKey

@Composable
fun NavigationRoot(viewModel: MainViewModel) {
    val backStack = rememberNavBackStack(OnboardingHome)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<OnboardingHome> {
                SelectDayScreen(viewModel, nextScreen = {
                    backStack.add(OnboardingLastCollectionType)
                })
            }
            entry<OnboardingLastCollectionType> {
                LastCollectionBinScreen(viewModel, nextScreen = {
                    backStack.add(RecycleBinColorSelection)
                })
            }

            entry<RecycleBinColorSelection> {
                BinColorScreens(BinType.RECYCLING, viewModel, nextScreen = {
                    backStack.add(GardenBinColorSelection)
                })
            }

            entry<GardenBinColorSelection> {
                BinColorScreens(BinType.GARDEN, viewModel, nextScreen = {
                    backStack.add(Home)
                }
                )
            }

            entry<Home> {
                HomeScreen()
            }

        }
    )
}
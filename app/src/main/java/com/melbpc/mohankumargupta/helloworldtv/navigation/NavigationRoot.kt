package com.melbpc.mohankumargupta.helloworldtv.navigation

//import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.melbpc.mohankumargupta.helloworldtv.BinType
import com.melbpc.mohankumargupta.helloworldtv.HomeScreen
import com.melbpc.mohankumargupta.helloworldtv.MainViewModel
//import com.melbpc.mohankumargupta.helloworldtv.Settings
import com.melbpc.mohankumargupta.helloworldtv.onboarding.BinColorScreens
import com.melbpc.mohankumargupta.helloworldtv.onboarding.LastCollectionBinScreen
import com.melbpc.mohankumargupta.helloworldtv.onboarding.SelectDayScreen
//import com.melbpc.mohankumargupta.helloworldtv.navigation.dataStore
//import com.melbpc.mohankumargupta.helloworldtv.onboarding.RecyclingBinColorScreen
import kotlinx.serialization.Serializable

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//private val android.content.Context.dataStore by preferencesDataStore("settings")

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
    val onboardingRequired = viewModel.isOnboardingRequired.collectAsState()
    var backStackNavKey: NavKey = OnboardingHome

    when (onboardingRequired.value) {
        null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                // You could also add Text("Loading app...")
            }
        }

        else -> {
            val key = if (onboardingRequired.value!!) OnboardingHome else Home
            val backStack = rememberNavBackStack(key)
            Navigation(backStack, viewModel)
        }
//        true -> {}
//        false -> {
//            backStackNavKey = Home
//        }
    }

//    val backStack = if (!settings.previousSettings()) rememberNavBackStack(OnboardingHome)
//                    else rememberNavBackStack(Home)

}

@Composable
private fun Navigation(
    backStack: NavBackStack,
    viewModel: MainViewModel
) {
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

            entry<RecycleBinColorSelection> //            (
//                metadata = NavDisplay.transitionSpec {
//                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(1000)) togetherWith ExitTransition.None
//                }
//
//            )
            {
                BinColorScreens(BinType.RECYCLING, viewModel, nextScreen = {
                    backStack.add(GardenBinColorSelection)
                })
            }

            entry<GardenBinColorSelection>(
                metadata = NavDisplay.transitionSpec {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(1000)
                    ) togetherWith fadeOut()
                    //slideOutHorizontally(targetOffsetX = { it   }, animationSpec = tween(1000, delayMillis = 1000))
                }
            ) {
                BinColorScreens(BinType.GARDEN, viewModel, nextScreen = {
                    viewModel.saveSettings(
                        viewModel.recyclingReferenceDate.value,
                        viewModel.gardenBinColor.value.name,
                        viewModel.recyclingBinColor.value.name
                    )
                    backStack.add(Home)
                }
                )
            }

            entry<Home> {
                HomeScreen(viewModel)
            }

        }
    )
}
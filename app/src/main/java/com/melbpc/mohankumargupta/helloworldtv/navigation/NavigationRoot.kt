package com.melbpc.mohankumargupta.helloworldtv.navigation

//import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
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
                    slideInHorizontally(initialOffsetX = { - it  }, animationSpec = tween(1000)) togetherWith fadeOut()
                            //slideOutHorizontally(targetOffsetX = { it   }, animationSpec = tween(1000, delayMillis = 1000))
                }
            ) {
                BinColorScreens(BinType.GARDEN, viewModel, nextScreen = {
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
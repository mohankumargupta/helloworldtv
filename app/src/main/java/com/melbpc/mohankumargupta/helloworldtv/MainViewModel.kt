package com.melbpc.mohankumargupta.helloworldtv

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val settings: Settings = Settings(application.applicationContext)

    private val _collectionDay = MutableStateFlow("Monday")
    val collectionDay = _collectionDay.asStateFlow()

    private val _recyclingReferenceDate = MutableStateFlow(LocalDate.MIN)
    val recyclingReferenceDate = _recyclingReferenceDate.asStateFlow()

    private val _recyclingBinColor = MutableStateFlow(ColorSwatch.Black)
    val recyclingBinColor = _recyclingBinColor.asStateFlow()

    private val _gardenBinColor = MutableStateFlow(ColorSwatch.Black)
    val gardenBinColor = _gardenBinColor.asStateFlow()

    private val _isOnboardingRequired = MutableStateFlow<Boolean?>(null)
    val isOnboardingRequired: StateFlow<Boolean?> = _isOnboardingRequired.asStateFlow()


    init {
        checkOnboardingRequired()
    }

    private fun checkOnboardingRequired() {
        // viewModelScope is crucial. It ties the coroutine to the ViewModel's lifecycle.
        // The coroutine will be automatically cancelled when the ViewModel is cleared.
        viewModelScope.launch {
            try {
                Log.d("mohan", "Checking onboarding status...")
                // Call your suspend function from the Settings class
                if (settings.hasAnyStoredPreferences()) {
                  _isOnboardingRequired.value = false
                } else {
                  _isOnboardingRequired.value = true
                }
            } catch (e: Exception) {
                _isOnboardingRequired.value = true
            }
        }
    }


    fun setCollectionDay(day: String)  {
        _collectionDay.value = day
    }

    fun setRecyclingReferenceDate(date: LocalDate) {
        _recyclingReferenceDate.value = date
    }

    fun setRecyclingBinColor(color: ColorSwatch) {
        _recyclingBinColor.value = color
    }

    fun setGardenBinColor(color: ColorSwatch) {
       _gardenBinColor.value = color
    }

    fun saveSettings(recyclingReferenceDate: LocalDate, gardenBinColor: ColorSwatch, recyclingBinColor: ColorSwatch) {
        viewModelScope.launch {
          settings.saveSettings(recyclingReferenceDate, gardenBinColor, recyclingBinColor)
          completeOnboarding()
        }
    }

    private fun completeOnboarding() {
        _isOnboardingRequired.value = false
    }

    private fun findNextDayOfWeek(targetDay: DayOfWeek, fromDate: LocalDate = LocalDate.now()): LocalDate {
        return fromDate.with(TemporalAdjusters.next(targetDay))
    }

    sealed class Bin(val availableColors: Map<ColorSwatch, Int>) {
        object Recycling : Bin(
            mapOf(
                ColorSwatch.Black to R.drawable.recycling_bin_black,
                ColorSwatch.Blue to R.drawable.recycling_bin_blue,
                ColorSwatch.DarkGreen to R.drawable.recycling_bin_darkgreen,
                ColorSwatch.Green to R.drawable.recycling_bin_green,
                ColorSwatch.Grey to R.drawable.recycling_bin_grey,
                ColorSwatch.Purple to R.drawable.recycling_bin_purple,
                ColorSwatch.Red to R.drawable.recycling_bin_red,
                ColorSwatch.Yellow to R.drawable.recycling_bin_yellow,
            )
        )

        object Garden : Bin(
            mapOf(
                ColorSwatch.Black to R.drawable.garden_bin_black,
                ColorSwatch.Blue to R.drawable.garden_bin_blue,
                ColorSwatch.DarkGreen to R.drawable.garden_bin_darkgreen,
                ColorSwatch.Green to R.drawable.garden_bin_green,
                ColorSwatch.Grey to R.drawable.garden_bin_grey,
                ColorSwatch.Purple to R.drawable.garden_bin_purple,
                ColorSwatch.Red to R.drawable.garden_bin_red,
                ColorSwatch.Yellow to R.drawable.garden_bin_yellow,
            )
        )

        fun getDrawable(color: ColorSwatch): Int? = availableColors[color]

    }




}
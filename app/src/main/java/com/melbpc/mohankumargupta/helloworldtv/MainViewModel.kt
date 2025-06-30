package com.melbpc.mohankumargupta.helloworldtv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

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


    //boarding status (true/false/null) ---
    // Initialize with 'null' to represent the undecided/loading state.
    private val _isOnboardingRequired = MutableStateFlow<Boolean?>(null)
    val isOnboardingRequired: StateFlow<Boolean?> = _isOnboardingRequired.asStateFlow()

//    // Sealed class to represent the different states for initial navigation
//    sealed class InitialNavTarget {
//        data object Loading : InitialNavTarget() // State while checking settings
//        data class Target(val key: NavKey) : InitialNavTarget() // State when target is determined
//    }
//
//    private val _initialNavTarget = MutableStateFlow<InitialNavTarget>(InitialNavTarget.Loading)
//    val initialNavTarget: StateFlow<InitialNavTarget> = _initialNavTarget.asStateFlow()
//

    init {
        checkOnboardingRequired()
    }

    private fun checkOnboardingRequired() {
        // viewModelScope is crucial. It ties the coroutine to the ViewModel's lifecycle.
        // The coroutine will be automatically cancelled when the ViewModel is cleared.
        viewModelScope.launch {
            try {
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

}
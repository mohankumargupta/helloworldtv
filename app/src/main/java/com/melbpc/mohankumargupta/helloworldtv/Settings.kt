package com.melbpc.mohankumargupta.helloworldtv

import android.content.Context
import android.util.Log
//import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeParseException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class Settings(private val context: Context) {

//    val recyclingBinColor: Flow<String> = context.dataStore.data
//        .map { preferences ->
//            preferences[Key.RECYCLING_BIN_COLOR] ?: ColorSwatch.Black.name
//        }
//
//    val gardenBinColor: Flow<String> = context.dataStore.data
//        .map { preferences ->
//            preferences[Key.GARDEN_BIN_COLOR] ?: ColorSwatch.Black.name
//        }
//
//    val recyclingReferenceDate: Flow<LocalDate> = context.dataStore.data
//        .map { preferences ->
//            val dateString = preferences[Key.RECYCLING_REFERENCE_DATE] ?: LocalDate.now().toString()
//            LocalDate.parse(dateString)
//        }
suspend fun getRecyclingReferenceDate(): LocalDate? {
    return try {
        val dateString = context.dataStore.data.map { it[Key.RECYCLING_REFERENCE_DATE] }.first()
        dateString?.let { LocalDate.parse(it) }
    } catch (e: DateTimeParseException) {
        Log.e("Settings", "Error parsing recycling reference date", e)
        null
    } catch (e: Exception) {
        Log.e("Settings", "Error reading recycling reference date", e)
        null
    }
}

    suspend fun getRecyclingBinColorString(): String? { // Return String for ColorSwatch.valueOf
        return try {
            context.dataStore.data.map { it[Key.RECYCLING_BIN_COLOR] }.first()
        } catch (e: Exception) {
            Log.e("Settings", "Error reading recycling bin color", e)
            null
        }
    }

    suspend fun getGardenBinColorString(): String? { // Return String for ColorSwatch.valueOf
        return try {
            context.dataStore.data.map { it[Key.GARDEN_BIN_COLOR] }.first()
        } catch (e: Exception) {
            Log.e("Settings", "Error reading garden bin color", e)
            null
        }
    }



    private object Key {
        val RECYCLING_REFERENCE_DATE = stringPreferencesKey("RECYCLING_REFERENCE_DATE")
        val GARDEN_BIN_COLOR = stringPreferencesKey("GARDEN_BIN_COLOR")
        val RECYCLING_BIN_COLOR = stringPreferencesKey("RECYCLING_BIN_COLOR")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("ONBOARDING_COMPLETED")
    }

    suspend fun saveSettings(recyclingReferenceDate: LocalDate, gardenBinColor: ColorSwatch, recyclingBinColor: ColorSwatch) {
        context.dataStore.edit { settings ->
            settings[Key.RECYCLING_REFERENCE_DATE] = recyclingReferenceDate.toString()
            settings[Key.GARDEN_BIN_COLOR] = gardenBinColor.name
            settings[Key.RECYCLING_BIN_COLOR] = recyclingBinColor.name
            settings[Key.ONBOARDING_COMPLETED] = true
        }
    }

    suspend fun hasAnyStoredPreferences(): Boolean {
        val preferences = context.dataStore.data.first()
        val onboardingCompleted = preferences[Key.ONBOARDING_COMPLETED] ?: false
        return onboardingCompleted
    }
}


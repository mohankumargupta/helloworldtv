package com.melbpc.mohankumargupta.helloworldtv

import android.content.Context
//import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.map
import java.time.LocalDate

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

//private val COLLECTION_DAY = stringPreferencesKey("collection_day")`
//private val Keys = object {
//    val GARDEN_BIN_COLOR = stringPreferencesKey("garden_bin_color")
//    val RECYCLING_BIN_COLOR = stringPreferencesKey("recycling_bin_color")
//    val RECYCLING_REFERENCE_DATE = stringPreferencesKey("recycling_reference_date")
//}

class Settings(private val context: Context) {

    private object Key {
        val RECYCLING_REFERENCE_DATE = stringPreferencesKey("RECYCLING_REFERENCE_DATE")
        val GARDEN_BIN_COLOR = stringPreferencesKey("GARDEN_BIN_COLOR")
        val RECYCLING_BIN_COLOR = stringPreferencesKey("RECYCLING_BIN_COLOR")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("ONBOARDING_COMPLETED")
    }

    suspend fun saveSettings(recyclingReferenceDate: LocalDate, gardenBinColor: String, recyclingBinColor: String) {
        context.dataStore.edit { settings ->
            settings[Key.RECYCLING_REFERENCE_DATE] = recyclingReferenceDate.toString()
            settings[Key.GARDEN_BIN_COLOR] = gardenBinColor
            settings[Key.RECYCLING_BIN_COLOR] = recyclingBinColor
            settings[Key.ONBOARDING_COMPLETED] = true
        }
    }

    /*
    suspend fun previousSettings(): Boolean {
        return context.dataStore.data.count() > 0
        //return dataStore.data.first().asMap().isNotEmpty()
    }
    */

    suspend fun hasAnyStoredPreferences(): Boolean {
        val preferences = context.dataStore.data.first()
        val onboardingCompleted = preferences[Key.ONBOARDING_COMPLETED] ?: false
        return onboardingCompleted



        //val boo = onboarding_completed.first()
        //Log.d("mohan", "Usernameflow: ${userNameFlow.count()}")
        //if
        //if (userNameFlow.val) Log.d("mohan", context.dataStore.data.toString()) else Log.d("mohan", "usernameflow not found")
        //return context.dataStore.data.count() > 0
    }
}


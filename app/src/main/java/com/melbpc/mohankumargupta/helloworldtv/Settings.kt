package com.melbpc.mohankumargupta.helloworldtv

import android.content.Context
import android.util.Log
//import android.provider.ContactsContract.Data
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import java.time.LocalDate

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

//private val COLLECTION_DAY = stringPreferencesKey("collection_day")`
//private val Keys = object {
//    val GARDEN_BIN_COLOR = stringPreferencesKey("garden_bin_color")
//    val RECYCLING_BIN_COLOR = stringPreferencesKey("recycling_bin_color")
//    val RECYCLING_REFERENCE_DATE = stringPreferencesKey("recycling_reference_date")
//}

class Settings(private val context: Context) {

    companion object {

        val recycling_reference_date = stringPreferencesKey("recycling_reference_date")
        val garden_bin_color = stringPreferencesKey("garden_bin_color")
        val recycling_bin_color = stringPreferencesKey("recycling_bin_color")
    }

    suspend fun saveSettings(recyclingReferenceDate: LocalDate, gardenBinColor: String, recyclingBinColor: String) {
        context.dataStore.edit { settings ->
            settings[recycling_reference_date] = recyclingReferenceDate.toString()
            settings[garden_bin_color] = gardenBinColor
            settings[recycling_bin_color] = recyclingBinColor
        }
    }

    suspend fun previousSettings(): Boolean {
        return context.dataStore.data.count() > 0
        //return dataStore.data.first().asMap().isNotEmpty()
    }

    suspend fun hasAnyStoredPreferences(): Boolean {
        val userNameFlow = context.dataStore.data
            .map { preferences ->
                preferences[recycling_reference_date]
            }
        //Log.d("mohan", "Usernameflow: ${userNameFlow.count()}")
        //if
        //if (userNameFlow.val) Log.d("mohan", context.dataStore.data.toString()) else Log.d("mohan", "usernameflow not found")
        return context.dataStore.data.count() > 0
    }
}


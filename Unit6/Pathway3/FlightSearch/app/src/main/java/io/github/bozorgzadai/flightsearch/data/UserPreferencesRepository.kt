package io.github.bozorgzadai.flightsearch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val SEARCH_QUERY = stringPreferencesKey("search_query")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun getInitialSearchQuery(): String {
        return try {
            dataStore.data.first()[SEARCH_QUERY] ?: ""
        } catch (e: IOException) {
            Log.e(TAG, "Error reading initial search query", e)
            "" // Safe fallback
        }
    }

    suspend fun saveSearchQueryPreference(searchQuery: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_QUERY] = searchQuery
        }
    }
}
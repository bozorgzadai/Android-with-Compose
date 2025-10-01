package io.github.bozorgzadai.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import androidx.datastore.preferences.core.Preferences


private const val SEARCH_QUERY_PREFERENCE_NAME = "search_query_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SEARCH_QUERY_PREFERENCE_NAME)

@Module
@InstallIn(SingletonComponent::class) // Lives for the entire app
object FlightModule {

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(@ApplicationContext context: Context): UserPreferencesRepository {
        return UserPreferencesRepository(context.dataStore)
    }

    @Provides
    @Singleton
    fun provideFlightDatabase(@ApplicationContext context: Context): FlightDatabase {
        return FlightDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFlightDao(database: FlightDatabase): FlightDao {
        return database.flightDao()
    }

    @Provides
    @Singleton
    fun provideFlightRepository(flightDao: FlightDao): FlightRepository {
        return OfflineFlightRepository(flightDao)
    }
}
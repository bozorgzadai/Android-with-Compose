package io.github.bozorgzadai.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getAirportsBySearchStream(query: String): Flow<List<Airport>>

    fun getFlightScheduleForStream(airportId: Int): Flow<List<Airport>>

    fun getAllFavoritesStream(): Flow<List<Favorite>>

    suspend fun getFavorite(departureCode: String, destinationCode: String): Favorite?

    suspend fun insertFavorite(favorite: Favorite): Long

    suspend fun deleteFavorite(favorite: Favorite)
}
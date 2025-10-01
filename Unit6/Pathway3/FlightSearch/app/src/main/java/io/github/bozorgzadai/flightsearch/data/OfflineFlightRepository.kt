package io.github.bozorgzadai.flightsearch.data

import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(private val flightDao: FlightDao) : FlightRepository {
    override fun getAirportsBySearchStream(query: String): Flow<List<Airport>> =
        flightDao.getAirportsBySearch(query)

    override fun getFlightScheduleForStream(airportId: Int): Flow<List<Airport>> =
        flightDao.getFlightScheduleFor(airportId)

    override fun getAllFavoritesStream(): Flow<List<Favorite>> = flightDao.getAllFavorites()

    override suspend fun getFavorite(departureCode: String, destinationCode: String): Favorite? =
        flightDao.getFavorite(departureCode, destinationCode)

    override suspend fun insertFavorite(favorite: Favorite): Long =
        flightDao.insertFavorite(favorite)

    override suspend fun deleteFavorite(favorite: Favorite) = flightDao.deleteFavorite(favorite)
}
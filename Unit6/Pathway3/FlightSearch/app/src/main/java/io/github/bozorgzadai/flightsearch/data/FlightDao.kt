package io.github.bozorgzadai.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {

    @Query("SELECT * FROM airport WHERE name LIKE '%' || :query || '%' OR iata_code LIKE '%' || :query || '%' ORDER BY passengers DESC")
    fun getAirportsBySearch(query: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE id != :airportId  ORDER BY passengers DESC")
    fun getFlightScheduleFor(airportId: Int): Flow<List<Airport>>

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode")
    suspend fun getFavorite(departureCode: String, destinationCode: String): Favorite?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite): Long

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}
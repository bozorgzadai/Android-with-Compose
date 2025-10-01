package io.github.bozorgzadai.flightsearch.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bozorgzadai.flightsearch.data.Airport
import io.github.bozorgzadai.flightsearch.data.Favorite
import io.github.bozorgzadai.flightsearch.data.FlightRepository
import io.github.bozorgzadai.flightsearch.data.UserPreferencesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

sealed interface FlightUiState {
    val currentQuery: String

    data class Favorites(
        override val currentQuery: String = "",
        val favorites: List<Favorite>
    ) : FlightUiState

    data class SearchedAirports(
        override val currentQuery: String,
        val airports: List<Airport>
    ) : FlightUiState

    data class FlightsForAirport(
        override val currentQuery: String,
        val airport: Airport,
        val flights: List<Airport>
    ) : FlightUiState
}

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val flightRepository: FlightRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _selectedAirportId = MutableStateFlow<Int?>(null)

    init {
        viewModelScope.launch {
            _searchQuery.value = userPreferencesRepository.getInitialSearchQuery()
        }
    }

    private val favoritesFlow = flightRepository.getAllFavoritesStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val airportsFlow = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            saveSearchQuery(query)

            if (query.isBlank()) {
                flowOf(emptyList())
            } else {
                flightRepository.getAirportsBySearchStream(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    private val flightsForAirport = _selectedAirportId
        .flatMapLatest { id ->
            if (id == null) flowOf(emptyList())
            else flightRepository.getFlightScheduleForStream(id)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    val uiState: StateFlow<FlightUiState> = combine(
        _searchQuery,
        favoritesFlow,
        airportsFlow,
        _selectedAirportId,
        flightsForAirport
    ) { query, favorites, airports, selectedId, flights ->
        when {
            selectedId != null -> {
                val selectedAirport = airports.find { it.id == selectedId }
                    ?: error("Selected airport not found")
                FlightUiState.FlightsForAirport(query, selectedAirport, flights)
            }

            query.isNotBlank() -> FlightUiState.SearchedAirports(query, airports)
            else -> FlightUiState.Favorites(query, favorites)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FlightUiState.Favorites("", emptyList())
    )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query

        // If user starts typing again, clear selection
        if (query.isNotBlank()) {
            _selectedAirportId.value = null
        }
    }

    fun saveSearchQuery(searchQuery: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveSearchQueryPreference(searchQuery)
        }
    }

    fun onAirportSelected(airport: Airport) {
        _selectedAirportId.value = airport.id
    }

    fun onStarClick(departureAirport: Airport, destinationAirport: Airport) {
        viewModelScope.launch {
            val favorite =
                flightRepository.getFavorite(departureAirport.iataCode, destinationAirport.iataCode)
            if (favorite != null) {
                flightRepository.deleteFavorite(favorite)
            } else {
                flightRepository.insertFavorite(
                    Favorite(
                        departureCode = departureAirport.iataCode,
                        destinationCode = destinationAirport.iataCode
                    )
                )
            }
        }
    }

    fun deleteFavorite(departureAirport: Airport, destinationAirport: Airport) {
        viewModelScope.launch {
            flightRepository.deleteFavorite(
                Favorite(
                    id = departureAirport.id,
                    departureCode = departureAirport.iataCode,
                    destinationCode = destinationAirport.iataCode
                )
            )
        }
    }
}
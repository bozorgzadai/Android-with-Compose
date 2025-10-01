package io.github.bozorgzadai.flightsearch.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.FlightSearchTheme
import io.github.bozorgzadai.flightsearch.R
import io.github.bozorgzadai.flightsearch.data.Airport
import io.github.bozorgzadai.flightsearch.data.Favorite

@Composable
fun FlightScreen(
    viewModel: FlightViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val searchQuery by viewModel.searchQueryState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            FlightSearchTopAppBar(
                title = stringResource(R.string.app_name),
            )
        }
    ) { contentPadding ->
        val layoutDirection = LocalLayoutDirection.current

        Surface(
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding(),
                start = contentPadding.calculateStartPadding(layoutDirection),
                end = contentPadding.calculateEndPadding(layoutDirection),
            )
        ) {
            Column {
                val currentState = uiState

//                OutlinedTextField(
//                    value = searchQuery,
//                    onValueChange = { /* handle input */ },
//                    label = { Text("Search") }
//                )

                AirportQuerySearch(
                    onSearchQueryChanged = viewModel::onSearchQueryChanged,
                    textSearch = when (currentState) {
                        is FlightUiState.Favorites -> ""
                        is FlightUiState.SearchedAirports -> currentState.currentQuery
                        is FlightUiState.FlightsForAirport -> currentState.airport.iataCode
                    }
                )

                when (currentState) {
                    is FlightUiState.Favorites -> {
                        FlightScheduleList(
                            textTitle = stringResource(R.string.favorite_routes),
                            favorites = currentState.favorites,
                            onFavoriteClick = { departureAirport, destinationAirport ->
                                viewModel.deleteFavorite(departureAirport, destinationAirport)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    is FlightUiState.SearchedAirports -> {
                        AirportSearch(
                            airportList = currentState.airports,
                            onAirportClick = viewModel::onAirportSelected
                        )
                    }

                    is FlightUiState.FlightsForAirport -> {
                        FlightScheduleList(
                            textTitle = stringResource(
                                R.string.flights_from,
                                currentState.airport.iataCode
                            ),
                            departureAirport = currentState.airport,
                            flightList = currentState.flights,
                            onFavoriteClick = { departureAirport, destinationAirport ->
                                viewModel.onStarClick(departureAirport, destinationAirport)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FlightScheduleList(
    onFavoriteClick: (Airport, Airport) -> Unit,
    textTitle: String,
    modifier: Modifier = Modifier,
    departureAirport: Airport? = null,
    flightList: List<Airport>? = null,
    favorites: List<Favorite>? = null,
) {

    Column(
        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small),
            top = dimensionResource(R.dimen.padding_small)
        ),
    ) {
        Text(
            text = textTitle,
            modifier = modifier,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        ) {
            if (flightList != null && departureAirport != null) {
                items(
                    items = flightList,
                    key = { airport -> airport.id }
                ) { airport ->
                    FlightCard(
                        departureAirport = departureAirport,
                        destinationAirport = airport,
                        onFavoriteClick = onFavoriteClick,
                        isFavorite = false,
                        modifier = modifier
                            .fillMaxWidth()
                    )

                }
            } else if (favorites != null) {
                items(
                    items = favorites,
                    key = { favorite -> favorite.id }
                ) { favorite ->
                    FlightCard(
                        departureAirport = Airport(
                            id = favorite.id,
                            iataCode = favorite.departureCode,
                            name = "",
                            passengers = 0
                        ),
                        destinationAirport = Airport(
                            id = favorite.id,
                            iataCode = favorite.destinationCode,
                            name = "",
                            passengers = 0
                        ),
                        onFavoriteClick = onFavoriteClick,
                        isFavorite = true,
                        modifier = modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun FlightCard(
    departureAirport: Airport,
    destinationAirport: Airport,
    onFavoriteClick: (Airport, Airport) -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.card_corner_radius),
            topStart = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(R.dimen.padding_small)
                )
        ) {
            Column {
                FlightItem(
                    itemTitle = R.string.depart,
                    airport = departureAirport,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                FlightItem(
                    itemTitle = R.string.arrive,
                    airport = destinationAirport,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(R.string.star),
                    tint = if (isFavorite) {
                        MaterialTheme.colorScheme.onTertiaryContainer
                    } else {
                        MaterialTheme.colorScheme.secondary
                    },
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.star_size))
                        .padding(
                            end = dimensionResource(R.dimen.padding_small)
                        )
                        .clickable {
                            onFavoriteClick.invoke(departureAirport, destinationAirport)
                        }
                )
            }
        }
    }
}

@Composable
fun FlightItem(
    @StringRes itemTitle: Int,
    airport: Airport,
) {
    Text(
        text = stringResource(itemTitle),
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Normal,
    )
    Row {
        Text(
            text = airport.iataCode,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
        Text(
            text = airport.name,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Normal,
        )
    }
}


@Composable
fun AirportSearch(
    airportList: List<Airport>,
    onAirportClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            top = dimensionResource(R.dimen.padding_small)
        ),
    ) {
        LazyColumn {
            items(
                items = airportList,
                key = { airport -> airport.id }
            ) { airport ->
                AirportItem(
                    airport = airport,
                    modifier = modifier
                        .clickable {
                            onAirportClick.invoke(airport)
                        }
                )

            }
        }
    }
}

@Composable
fun AirportItem(
    airport: Airport,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = airport.iataCode,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
        Text(
            text = airport.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
        )
    }
}


@Composable
fun AirportQuerySearch(
    onSearchQueryChanged: (String) -> Unit,
    textSearch: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textSearch,
        onValueChange = { onSearchQueryChanged(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon)
            )
        },
        trailingIcon = null,
        placeholder = {
            Text(
                stringResource(R.string.enter_departure_airport),
            )
        },
        shape = RoundedCornerShape(dimensionResource(R.dimen.search_rounded_corner)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchTopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}


@Preview(showBackground = true)
@Composable
fun FlightScreenPreview() {
    FlightSearchTheme {
//        FlightSearch()
    }
}

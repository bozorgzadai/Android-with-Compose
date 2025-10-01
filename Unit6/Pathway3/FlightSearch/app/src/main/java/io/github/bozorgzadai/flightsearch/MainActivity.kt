package io.github.bozorgzadai.flightsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.FlightSearchTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.bozorgzadai.flightsearch.ui.screen.FlightScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightSearchTheme {
                FlightScreen()
            }
        }
    }
}
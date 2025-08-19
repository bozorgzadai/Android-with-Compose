package io.github.bozorgzadai.paristourapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import io.github.bozorgzadai.paristourapp.ui.ParisTourApp
import io.github.bozorgzadai.paristourapp.ui.theme.ParisTourAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParisTourAppTheme {
                val windowSize = calculateWindowSizeClass(this)
                ParisTourApp(
                    windowSize = windowSize.widthSizeClass,
                )
            }
        }
    }
}

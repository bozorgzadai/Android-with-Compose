package io.github.bozorgzadai.a17daysofgadgets

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.bozorgzadai.a17daysofgadgets.model.GadgetRepo
import io.github.bozorgzadai.a17daysofgadgets.ui.theme._17DaysOfGadgetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _17DaysOfGadgetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GadgetsApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GadgetsApp(modifier: Modifier = Modifier) {
    val gadgets = GadgetRepo.gadgets

    Scaffold(
        topBar = {
            GadgetsTopAppBar()
        },
        modifier = modifier,
    ) {
        GadgetList(
            gadgets = gadgets,
            contentPadding = it,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GadgetsTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        modifier = modifier,
    )
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GadgetsPreview() {
    _17DaysOfGadgetsTheme {
        GadgetsApp()
    }
}
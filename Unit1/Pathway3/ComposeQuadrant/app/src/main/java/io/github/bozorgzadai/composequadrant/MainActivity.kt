package io.github.bozorgzadai.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.bozorgzadai.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComposeQuadrant(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CellContent(title: String, desc: String, bgColor: Color, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = desc,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
fun ComposeQuadrant(modifier: Modifier = Modifier) {
    Column {
        val myModifier = Modifier.weight(0.5F)

        Row(modifier = myModifier) {
            CellContent(
                title = stringResource(R.string.text_composable_title),
                desc = stringResource(R.string.text_composable_desc),
                bgColor = Color(0xFFEADDFF),
                modifier = myModifier,
            )
            CellContent(
                title = stringResource(R.string.image_composable_title),
                desc = stringResource(R.string.image_composable_desc),
                bgColor = Color(0xFFD0BCFF),
                modifier = myModifier,
            )
        }
        Row(modifier = myModifier) {
            CellContent(
                title = stringResource(R.string.row_composable_title),
                desc = stringResource(R.string.row_composable_desc),
                bgColor = Color(0xFFB69DF8),
                modifier = myModifier,
            )
            CellContent(
                title = stringResource(R.string.column_composable_title),
                desc = stringResource(R.string.column_composable_desc),
                bgColor = Color(0xFFF6EDFF),
                modifier = myModifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrant()
    }
}
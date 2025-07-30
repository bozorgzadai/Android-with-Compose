package io.github.bozorgzadai.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bozorgzadai.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ArtSpaceApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    ArtSpaceTheme {
        MainPage()
    }
}

@Composable
fun MainPage() {
    var currentImage: Int by remember { mutableIntStateOf(1) }

    var imageId: Int = R.drawable.mona_lisa
    var imageTitle: Int = R.string.mona_lisa_title
    var imageArtist: Int = R.string.mona_lisa_artist
    var imageYear: Int = R.string.mona_lisa_year

    when (currentImage) {
        2 -> {
            imageId = R.drawable.the_starry_night
            imageTitle = R.string.starry_night_title
            imageArtist = R.string.starry_night_artist
            imageYear = R.string.starry_night_year
        }

        3 -> {
            imageId = R.drawable.girl_with_a_pearl_earring
            imageTitle = R.string.girl_with_pearl_earring_title
            imageArtist = R.string.girl_with_pearl_earring_artist
            imageYear = R.string.girl_with_pearl_earring_year
        }

        4 -> {
            imageId = R.drawable.the_great_wave_off_kanagawa
            imageTitle = R.string.great_wave_off_kanagawa_title
            imageArtist = R.string.great_wave_off_kanagawa_artist
            imageYear = R.string.great_wave_off_kanagawa_year
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFBFD))
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        ImageSection(
            imageId = imageId,
            imageDesc = imageTitle,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0F)
                .padding(bottom = 30.dp)
        )

        DescSection(
            imageTitle = imageTitle,
            imageArtist = imageArtist,
            imageYear = imageYear,
            modifier = Modifier
                .background(Color(0xFFECEBF0))
                .padding(vertical = 20.dp)
                .padding(horizontal = 15.dp)
        )

        ButtonsSection(
            btnNextClicked = {
                when (currentImage) {
                    in 1..3 -> currentImage++
                    4 -> currentImage = 1
                }
            },
            btnPreviousClicked = {
                when (currentImage) {
                    in 2..4 -> currentImage--
                    1 -> currentImage = 4
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ImageSection(
    @DrawableRes imageId: Int,
    @StringRes imageDesc: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.Black,
                )
                .border(
                    border = BorderStroke(
                        width = 1.dp, color = Color(0xFFEEECEE)
                    )
                )
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(imageDesc),
                modifier = Modifier.padding(30.dp)
            )
        }
    }
}

@Composable
fun DescSection(
    @StringRes imageTitle: Int,
    @StringRes imageArtist: Int,
    @StringRes imageYear: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = stringResource(imageTitle),
            fontFamily = FontFamily.Default,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Row {
            Text(
                text = stringResource(imageArtist),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = stringResource(imageYear),
                fontSize = 15.sp,
            )
        }
    }
}

@Composable
fun ButtonsSection(
    btnNextClicked: () -> Unit,
    btnPreviousClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        val btnModifier = Modifier.size(width = 130.dp, height = 45.dp)
        Button(
            modifier = btnModifier,
            onClick = btnPreviousClicked,
        ) {
            Text(
                text = "Previous"
            )
        }
        Spacer(Modifier.weight(1.0F))
        Button(
            modifier = btnModifier,
            onClick = btnNextClicked,
        ) {
            Text(
                text = "Next"
            )
        }
    }
}

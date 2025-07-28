package io.github.bozorgzadai.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bozorgzadai.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    ImageWithText(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}

@Composable
fun ImageWithText(modifier: Modifier = Modifier) {
    var pageNum: Int by remember { mutableIntStateOf(1) }
    var randNumber: Int = -1

    var imageResource: Int = R.drawable.lemon_tree
    var title: String = stringResource(R.string.tree_text)
    var imgDesc: String = stringResource(R.string.img_desc_lemon_tree)

    when (pageNum) {
        2 -> {
            imageResource = R.drawable.lemon_squeeze
            title = stringResource(R.string.squeeze_text)
            imgDesc = stringResource(R.string.img_desc_lemon_squeeze)
        }
        3 -> {
            imageResource = R.drawable.lemon_drink
            title = stringResource(R.string.drink_text)
            imgDesc = stringResource(R.string.img_desc_lemon_drink)
        }
        4 -> {
            imageResource = R.drawable.lemon_restart
            title = stringResource(R.string.restart_text)
            imgDesc = stringResource(R.string.img_desc_lemon_restart)
        }
    }

    LemonImageAndText(imageResource, imgDesc, title, modifier) {
        when (pageNum) {
            1 -> pageNum++
            2 -> {
                if (randNumber == -1) {
                    randNumber = (2..4).random()
                    randNumber--
                } else if (randNumber == 0) {
                    pageNum++
                }
                randNumber--
            }

            3 -> pageNum++
            else -> pageNum = 1
        }
    }
}

@Composable
fun LemonImageAndText(
    imageResource: Int,
    imgDesc: String,
    title: String,
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onImageClicked,
            shape = RoundedCornerShape(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC3ECD2),
            )
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = imgDesc,
                modifier = Modifier
                    .padding(20.dp)
            )
        }
        Spacer(Modifier.height(32.dp))
        Text(title, fontSize = 18.sp)
    }
}

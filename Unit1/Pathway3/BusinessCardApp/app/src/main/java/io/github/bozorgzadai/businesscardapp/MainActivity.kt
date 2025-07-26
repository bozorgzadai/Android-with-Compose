package io.github.bozorgzadai.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bozorgzadai.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCardApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TitleSection() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val rowModifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth()

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            val image = painterResource(R.drawable.android_logo)
            Image(
                painter = image,
                contentDescription = "Android Logo",
                modifier = Modifier
                    .padding(5.dp)
                    .size(120.dp)
                    .background(Color(0xFF182C35))
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            Text(
                text = stringResource(R.string.full_name),
                fontSize = 32.sp,
                color = Color(0xFF0C160F)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier.padding(bottom = 0.dp)
        ) {
            Text(
                text = stringResource(R.string.title),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF296046)
            )
        }
    }
}

@Composable
fun ContactRowContent(icon: ImageVector, iconDesc: String, text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .padding(start = 65.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDesc,
            tint = Color(0xFF296047),
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            color = Color(0xFF060706)
        )
    }
}

@Composable
fun ContactSection() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .padding(bottom = 32.dp)
    ) {
        ContactRowContent(Icons.Filled.Phone, "Phone Number", stringResource(R.string.phone_number))
        ContactRowContent(Icons.Filled.Share, "Share", stringResource(R.string.social_media_handler))
        ContactRowContent(Icons.Filled.Email, "Email", stringResource(R.string.email))
    }
}

@Composable
fun BusinessCardApp(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .background(Color(0xFFDAE5DB))
            .fillMaxSize()
            .padding(16.dp)
            .padding(start = 4.dp)
    ) {
        Spacer(Modifier.weight(1.0F))
        TitleSection()
        Spacer(Modifier.weight(1.0F))
        ContactSection()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
        BusinessCardApp()
    }
}
package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Article(
                        title = stringResource(R.string.article_compose_title),
                        desc = stringResource(R.string.article_compose_desc),
                        tutorial = stringResource(R.string.article_compose_tutorial),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}


@Composable
fun Article(title: String, desc: String, tutorial: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.article_compose)
    Column {
        Image(
            painter = image,
            contentDescription = null,
        )

        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp),
        )

        Text(
            text = desc,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            ),
        )

        Text(
            text = tutorial,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        Article(
            title = stringResource(R.string.article_compose_title),
            desc = stringResource(R.string.article_compose_desc),
            tutorial = stringResource(R.string.article_compose_tutorial),
        )
    }
}
package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.R
import com.example.jetpackcomposetutorial.R.drawable.bg_compose_background


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        pageTitle = stringResource(R.string.pageTitle),
                        text1 = stringResource(R.string.textBody1),
                        text2 = stringResource(R.string.textBody2),
                    )
                }
        }
    }
}

@Composable
fun GreetingText(pageTitle: String, text1: String, text2: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Text(
            text = pageTitle,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = text1,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = text2,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(pageTitle: String, text1: String, text2: String, modifier: Modifier = Modifier) {
    val image = painterResource(bg_compose_background)
    Column(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
        )
        GreetingText(
            pageTitle = pageTitle,
            text1 = text1,
            text2 = text2,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
        GreetingImage(
            pageTitle = stringResource(R.string.pageTitle),
            text1 = stringResource(R.string.textBody1),
            text2 = stringResource(R.string.textBody2),
        )
}
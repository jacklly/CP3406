package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = MaterialTheme.colorScheme.background,
            ) {
                BoxText1(
                    sectionTitle = "Text composable",
                    bodyText = "Displays text and follows the recommended Material Design guidelines."
                )

                BoxText2(
                    sectionTitle = "Image composable",
                    bodyText = "Creates a composable that lays out and draws a given Painter class object."
                )

                BoxText3(
                    sectionTitle = "Row composable",
                    bodyText = "A layout composable that places its children in a horizontal sequence."
                )

                BoxText4(
                    sectionTitle = "Column composable",
                    bodyText = "A layout composable that places its children in a vertical sequence."
                )
            }
        }
    }
}

@Composable
fun BoxText1(sectionTitle: String, bodyText: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = sectionTitle,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = bodyText,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun BoxText2(sectionTitle: String, bodyText: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = sectionTitle,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = bodyText,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun BoxText3(sectionTitle: String, bodyText: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = sectionTitle,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = bodyText,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun BoxText4(sectionTitle: String, bodyText: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = sectionTitle,
            fontSize = 30.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(16.dp)
        )

        Text(
            text = bodyText,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
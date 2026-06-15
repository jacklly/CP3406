package com.example.businesscard

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Column(modifier = Modifier.fillMaxSize().background(Color.Cyan),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    Hero(
                        name = "John Smith",
                        title = "Android Dev Extraordinaire",
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    InfoSection(
                        phone = "+123456789",
                        mediaHandle = "JohnSmith123",
                        email = "JohnSmith@hotmail.com",
                    )

                    Spacer(
                        modifier = Modifier.height(50.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Hero(name: String, title: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val image = painterResource(R.drawable.android_logo)

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.background(Color.Black)
        )

        ShowName(name)

        ShowTitle(title)
    }
}

@Composable
fun ShowName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        fontSize = 40.sp,
        lineHeight = 42.sp,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun ShowTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontSize = 25.sp,
        lineHeight = 27.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun InfoSection(phone: String, mediaHandle: String, email: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.padding(bottom = 30.dp)) {

        Row(modifier = Modifier
            .padding(vertical = 5.dp,
                ) ) {
            val image = painterResource(R.drawable.baseline_local_phone_24)
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = phone,
                fontSize = 25.sp
            )
        }

        Row(modifier = Modifier
            .padding(vertical = 5.dp) ) {
            val image = painterResource(R.drawable.outline_add_24)
            Image(
                painter = image,
                contentDescription = null,
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = mediaHandle,
                fontSize = 25.sp
                )
        }

        Row(modifier = Modifier
            .padding(vertical = 5.dp) ) {
            val image = painterResource(R.drawable.outline_attach_email_24)
            Image(
                painter = image,
                contentDescription = null,
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = email,
                fontSize = 25.sp
            )
        }
    }
}

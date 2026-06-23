package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.apiCaller.RetrofitCall

/*home screen that displays the weather values*/
@Composable
fun HomeScreen() {
    Column {
        ShowWeather()
    }
}

/*call the weather api*/
@Composable
fun ShowWeather() {
    var result by remember { mutableStateOf<String>("Loading...") }

    LaunchedEffect(Unit) {
        val url =
            "v1/current.json?key=21c566a7c05445ce98833155262306&q=Townsville&aqi=no"

        try {
            val response = RetrofitCall.api.getWeather(url)
            result = response.string()
        } catch (e: Exception) {
            result = e.message ?: "Error"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Space Weather API")
        Spacer(Modifier.height(16.dp))
        Text(result)
    }
}
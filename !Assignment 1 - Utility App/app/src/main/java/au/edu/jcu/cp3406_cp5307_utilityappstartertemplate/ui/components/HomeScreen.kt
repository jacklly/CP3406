package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.R
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.apiCaller.RetrofitCall
import org.json.JSONObject

/*home screen that displays the weather values*/
@Composable
fun HomeScreen(chosenCity: String) {
    Column {
        ShowWeather(chosenCity = chosenCity)
    }
}

/*reponse from the api:
* {
    "location": {
        "name": "Townsville",
        "region": "Queensland",
        "country": "Australia",
        "lat": -19.25,
        "lon": 146.8,
        "tz_id": "Australia/Brisbane",
        "localtime_epoch": 1782373986,
        "localtime": "2026-06-25 17:53"
    },
    "current": {
        "last_updated_epoch": 1782373500,
        "last_updated": "2026-06-25 17:45",
        "temp_c": 23.2,
        "temp_f": 73.8,
        "is_day": 0,
        "condition": {
            "text": "Overcast",
            "icon": "//cdn.weatherapi.com/weather/64x64/night/122.png",
            "code": 1009
        },
        "wind_mph": 14.8,
        "wind_kph": 23.8,
        "wind_degree": 108,
        "wind_dir": "ESE",
        "pressure_mb": 1020.0,
        "pressure_in": 30.12,
        "precip_mm": 0.36,
        "precip_in": 0.01,
        "humidity": 65,
        "cloud": 75,
        "feelslike_c": 25.2,
        "feelslike_f": 77.3,
        "windchill_c": 21.7,
        "windchill_f": 71.0,
        "heatindex_c": 24.4,
        "heatindex_f": 76.0,
        "dewpoint_c": 17.2,
        "dewpoint_f": 63.0,
        "vis_km": 10.0,
        "vis_miles": 6.0,
        "uv": 0.2,
        "gust_mph": 19.9,
        "gust_kph": 32.1,
        "will_it_rain": 0,
        "chance_of_rain": 36,
        "will_it_snow": 0,
        "chance_of_snow": 0,
        "short_rad": 0,
        "diff_rad": 0,
        "dni": 0,
        "gti": 0
    }
}
* */

/*call the weather api*/
@Composable
fun ShowWeather(chosenCity: String) {
    var cityName by remember { mutableStateOf<String>("Loading...") }
    var temperature by remember { mutableStateOf<Double>(0.0) }
    var currentTime by remember { mutableStateOf<Int>(1) }
    //var cityName by remember { mutableStateOf<String>("Loading...") }

    LaunchedEffect(chosenCity) {
        val url = "v1/current.json?key=21c566a7c05445ce98833155262306&q=$chosenCity&aqi=no"

        val apiData = RetrofitCall.api.getWeather(url)

        val apiString: String = apiData.string()

        val apiJson = JSONObject(apiString)

        val location = apiJson.getJSONObject("location")
        val current = apiJson.getJSONObject("current")

        val isDay: Int = current.getInt("is_day")
        val city: String = location.getString("name")
        val temp: Double = current.getDouble("temp_c")

        currentTime = isDay
        temperature = temp
        cityName = city
    }
    Column(){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Queensland Weather API Service",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            text = cityName,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.LightGray
        )

        //Check for day/night and set background image
        var weatherImage: Painter
        var tempColor: Color
        if (currentTime == 1) {
            weatherImage = painterResource(id = R.drawable.day)
            tempColor = Color.White
        }
        else {
            weatherImage = painterResource(id = R.drawable.night)
            tempColor = Color.Black
        }

        Box(
            modifier = Modifier
                .size(544.dp, 359.dp)
                .paint(weatherImage, contentScale = ContentScale.FillBounds)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(80.dp),
                text = temperature.toString() + "℃",
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                color = tempColor
            )
        }
    }
}
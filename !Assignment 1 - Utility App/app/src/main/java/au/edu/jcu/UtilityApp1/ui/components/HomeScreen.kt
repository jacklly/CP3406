package au.edu.jcu.UtilityApp1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.edu.jcu.UtilityApp1.apiCaller.RetrofitCall
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.R
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
    var temperature by remember { mutableStateOf<Double>(100.0) }
    var currentTime by remember { mutableStateOf<Int>(1) }
    var temperatureLike by remember { mutableStateOf<Double>(100.0) }
    var humidityValue by remember { mutableStateOf<Int>(100) }
    var rainValue by remember { mutableStateOf<Int>(100) }
    var windValue by remember { mutableStateOf<Double>(100.0) }

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
        val feelsLike: Double = current.getDouble("feelslike_c")
        val humidity: Int = current.getInt("humidity")
        val rain: Int = current.getInt("chance_of_rain")
        val wind: Double = current.getDouble("wind_kph")

        //Set the usable var's from the api val's
        currentTime = isDay
        temperature = temp
        temperatureLike = feelsLike
        cityName = city
        humidityValue = humidity
        rainValue = rain
        windValue = wind
    }

    //UI DESIGN

    Box {
        //Set background based on day/night
        if (currentTime == 1) {
            Image(
                painter = painterResource(R.drawable.day),
                contentDescription = "day time image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(R.drawable.night),
                contentDescription = "night time image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column {

            //Variable header text colour
            var textColour: Color = Color.Black
            if (currentTime == 0) {
                textColour = Color.White
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Queensland Weather API Service",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = textColour
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = cityName,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = textColour
            )

            //Home image with main temp info
            Card (
                modifier = Modifier.padding(80.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFf8ca7c).copy(0.5f))
            ) {
                Row (
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_device_thermostat_24),
                        contentDescription = "temperature icon",
                        modifier = Modifier.size(60.dp),
                        tint = textColour
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "$temperature℃",
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        color = textColour
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Feels Like: $temperatureLike℃",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = textColour
                )
            }

            //Humidity bar dark blue
            Card(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF477F89).copy(0.5f))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_water_voc_24),
                            contentDescription = "humidity icon",
                            modifier = Modifier.padding(8.dp),
                            tint = textColour
                        )

                        Text(
                            text = "Humidity: $humidityValue%",
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                            fontSize = 25.sp,
                            lineHeight = 30.sp,
                            textAlign = TextAlign.Center,
                            color = textColour
                        )
                    }

                    Box {
                        LinearProgressIndicator(
                            color = Color(0xFF000080),
                            progress = { humidityValue / 100f },
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .height(25.dp)
                                .fillMaxWidth(),
                        )
                    }
                }
            }

            //Rain bar light blue
            Card(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF477F89).copy(0.5f))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_water_drops_24),
                            contentDescription = "rain icon",
                            modifier = Modifier.padding(8.dp),
                            tint = textColour
                        )

                        Text(
                            text = "Chance of rain: $rainValue%",
                            modifier = Modifier.padding(8.dp),
                            fontSize = 25.sp,
                            lineHeight = 30.sp,
                            textAlign = TextAlign.Center,
                            color = textColour
                        )
                    }

                    Box {
                        LinearProgressIndicator(
                            color = Color(0xFFADD8E6),
                            progress = { rainValue / 100f },
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .height(25.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }

            //Wind kph
            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF477F89).copy(0.5f))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_wind_power_24),
                        contentDescription = "wind icon",
                        modifier = Modifier.padding(8.dp),
                        tint = textColour
                    )

                    Text(
                        text = "Windspeed: $windValue Km/hr",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 25.sp,
                        color = textColour
                    )
                }
            }
        }
    }
}
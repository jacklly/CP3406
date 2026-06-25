package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.components

import android.content.ClipData
import android.support.v4.app.RemoteActionCompatParcelizer.read
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import kotlin.jvm.java

/*home screen that displays the weather values*/
@Composable
fun HomeScreen() {
    Column {
        ShowWeather()
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
fun ShowWeather() {
    var result by remember { mutableStateOf<String>("Loading...") }

    LaunchedEffect(Unit) {
        val url = "v1/current.json?key=21c566a7c05445ce98833155262306&q=$cityValue&aqi=no"

        val apiData = RetrofitCall.api.getWeather(url)

        val apiString: String = apiData.string()

        val apiJson = JSONObject(apiString)

        val location = apiJson.getJSONObject("location")
        val current = apiJson.getJSONObject("current")

        val city: String = location.getString("name")
        val temp: Double = current.getDouble("temp_c")


    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Weather API")
        Spacer(Modifier.height(16.dp))
        Text(result)
    }
}
package au.edu.jcu.UtilityApp1.apiCaller

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

//Call URL to get response
interface WeatherApi {
    @GET
    suspend fun getWeather(
        @Url url: String
    ): ResponseBody
}
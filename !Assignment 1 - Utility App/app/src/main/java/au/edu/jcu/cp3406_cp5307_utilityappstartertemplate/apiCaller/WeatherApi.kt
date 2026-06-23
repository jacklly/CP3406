package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.apiCaller

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

//Call URL to get response
interface WeatherApi {
    @GET
    suspend fun getWeather(
        @Url url: String
    ): ResponseBody
}
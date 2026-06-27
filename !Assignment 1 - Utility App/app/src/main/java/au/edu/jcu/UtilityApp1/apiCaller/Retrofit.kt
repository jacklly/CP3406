package au.edu.jcu.UtilityApp1.apiCaller

import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCall {

    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    val api: WeatherApi by lazy {
        Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
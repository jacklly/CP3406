package com.packt.chaptersix.di

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.packt.chaptersix.data.CatsAPI
import com.packt.chaptersix.data.PetsRepository
import com.packt.chaptersix.data.PetsRepositoryImpl
import com.packt.chaptersix.viewmodel.PetsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import java.io.File

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl(get(), get()) }
    single { Dispatchers.IO }
    viewModelOf(::PetsViewModel)
    single {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val cache = Cache(File(androidContext().cacheDir, "http_cache"), cacheSize)
        OkHttpClient.Builder()
            .cache(cache)
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl("https://cataas.com/api/")
            .build()
    }
    single { get<Retrofit>().create(CatsAPI::class.java) }
}
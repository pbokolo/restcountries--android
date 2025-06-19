package com.example.restcountries.data

import com.example.restcountries.network.RestCountriesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

interface AppContainer{
    val restCountriesRepository: RestCountriesRepository
}

class DefaultAppContainer: AppContainer{

    private val baseUrl = "https://restcountries.com/v3.1/"

    private val json = Json { ignoreUnknownKeys = true }

    // The following is used to control read, write and connect timeout with retrofit
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .baseUrl(baseUrl).client(client).build()

    private val retrofitService: RestCountriesApiService by lazy{
        retrofit.create(RestCountriesApiService::class.java)
    }

    override val restCountriesRepository: RestCountriesRepository by lazy {
        NetworkRestCountriesRepository(retrofitService)
    }

}
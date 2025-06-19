package com.example.restcountries.network

import com.example.restcountries.model.CountryDto
import retrofit2.http.GET

interface RestCountriesApiService {
    @GET("all?fields=name,capital,region,population,flags,currencies")
    suspend fun getAllCountries(): List<CountryDto>
}
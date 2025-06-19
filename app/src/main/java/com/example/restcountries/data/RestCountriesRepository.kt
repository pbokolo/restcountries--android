package com.example.restcountries.data

import com.example.restcountries.model.CountryDto
import com.example.restcountries.network.RestCountriesApiService

interface RestCountriesRepository{
    suspend fun getAll(): List<CountryDto>
}

/**
 * This one exposes data to the rest of the app
 */
class NetworkRestCountriesRepository(
    private val restCountriesApiService: RestCountriesApiService
): RestCountriesRepository{
    override suspend fun getAll(): List<CountryDto> {
        return restCountriesApiService.getAllCountries()
    }

}
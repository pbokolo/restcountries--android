package com.example.restcountries.data

import com.example.restcountries.model.Country
import com.example.restcountries.model.CountryDto
import com.example.restcountries.network.RestCountriesApiService

val countries = listOf<Country>(
    Country(
    name = "Democratic Republic of the Congo",
    capitalCity = "Kinshasa",
    region = "Africa",
    population = 95000000,
    currency = "CDF (Congolese Franc)",
    flagUrl = "https://flagcdn.com/w320/cd.png"
),
    Country(
        name = "Portugal",
        capitalCity = "Lisbon",
        region = "Europe",
        population = 10300000,
        currency = "EUR (Euro)",
        flagUrl = "https://flagcdn.com/w320/pt.png"
    ),
    Country(
        name = "Tanzania",
        capitalCity = "Dodoma",
        region = "Africa",
        population = 61000000,
        currency = "TZS (Tanzanian Shilling)",
        flagUrl = "https://flagcdn.com/w320/tz.png"
    ),
    Country(
        name = "South Africa",
        capitalCity = "Pretoria",
        region = "Africa",
        population = 60000000,
        currency = "ZAR (South African Rand)",
        flagUrl = "https://flagcdn.com/w320/za.png"
    )
)

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
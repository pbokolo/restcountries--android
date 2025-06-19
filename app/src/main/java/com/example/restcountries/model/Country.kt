package com.example.restcountries.model

import kotlinx.serialization.Serializable

/**
 * The following data class matches exactly what comes from the api
 * It's the first layer of serialization
 */
@Serializable
data class CountryDto(
    val name: Name? = null,
    val capital: List<String>? = null,
    val region: String? = null,
    val population: Long? = null,
    val currencies: Map<String, Currency>? = null,
    val flags: Flags? = null
)

@Serializable
data class Name(val common: String? = null)

@Serializable
data class Currency(val name: String? = null)

@Serializable
data class Flags(val png: String? = null)

/**
 * The following is the cleaner model of a country. It's what will be used by the UI
 */
data class Country(
    val name: String,
    val capitalCity: String,
    val region: String,
    val population: Long,
    val currency: String,
    val flagUrl: String
)

/**
 * Converts the DTO(Data Transfert Object) to the UI model
 */
fun CountryDto.toCountry(): Country {
    return Country(
        name = name?.common ?: "Unknown",
        capitalCity = capital?.firstOrNull() ?: "Unknown",
        region = region ?: "Unknown",
        population = population ?: 0L,
        currency = currencies?.values?.firstOrNull()?.name ?: "Unknown",
        flagUrl = flags?.png ?: ""
    )
}
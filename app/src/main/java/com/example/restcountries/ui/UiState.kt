package com.example.restcountries.ui

import com.example.restcountries.model.Country
import com.example.restcountries.utils.DataLoadingStates


data class UiState(
    val countriesList: List<Country> = listOf<Country>(),
    val selectedCountry: Country? = null,
    val dataLoadingState: String = DataLoadingStates.Loading.name,
    val errorMessage: String = ""
)
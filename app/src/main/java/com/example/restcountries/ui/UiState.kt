package com.example.restcountries.ui

import com.example.restcountries.utils.DataLoadingStates


data class UiState(
    val dataLoadingState: String = DataLoadingStates.Loading.name,
    val errorMessage: String = ""
)
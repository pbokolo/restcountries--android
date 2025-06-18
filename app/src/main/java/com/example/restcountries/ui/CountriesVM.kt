package com.example.restcountries.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountriesVM:  ViewModel(){

    // Private mutable version of the ui state
    private val _uiState = MutableStateFlow(UiState())
    // Public unmutable and observable version of the ui state
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getCountries()
    }

    fun getCountries(){


    }

}
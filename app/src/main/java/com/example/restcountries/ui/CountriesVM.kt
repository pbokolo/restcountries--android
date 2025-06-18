package com.example.restcountries.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restcountries.utils.DataLoadingStates
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesVM:  ViewModel(){

    // Private mutable version of the ui state
    private val _uiState = MutableStateFlow(UiState())
    // Public unmutable and observable version of the ui state
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getCountries()
    }

    fun getCountries(){
        viewModelScope.launch {
            // Set loading state before the delay
            _uiState.update { it.copy(dataLoadingState = DataLoadingStates.Loading.name) }

            delay(3000) // Simulate a network call that takes 3 seconds

            _uiState.update {
                it.copy(dataLoadingState = DataLoadingStates.Ready.name)
            }

            // After delay, randomly updates with Ready or Error(--Test only)
            /*_uiState.update {
                it.copy(
                    dataLoadingState = if (Random.nextBoolean())
                        DataLoadingStates.Ready.name
                    else
                        DataLoadingStates.Error.name
                )
            }*/
        }

    }

}
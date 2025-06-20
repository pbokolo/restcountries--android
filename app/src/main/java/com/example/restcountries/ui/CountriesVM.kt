package com.example.restcountries.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.restcountries.RestCountriesApplication
import com.example.restcountries.data.RestCountriesRepository
import com.example.restcountries.model.toCountry
import com.example.restcountries.utils.DataLoadingStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException

class CountriesVM(private val restCountriesRepo: RestCountriesRepository):  ViewModel(){

     companion object {
         val Factory: ViewModelProvider.Factory = viewModelFactory {
             initializer {
                 val application = (this[APPLICATION_KEY] as RestCountriesApplication)
                 val countriesRepo = application.container.restCountriesRepository
                 CountriesVM(restCountriesRepo =  countriesRepo)
             }
         }
     }

    // Private mutable version of the ui state
    private val _uiState = MutableStateFlow(UiState())
    // Public unmutable and observable version of the ui state
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getCountries()
    }

    fun getCountries(){
        viewModelScope.launch {

            try {
                _uiState.update { it.copy(dataLoadingState = DataLoadingStates.Loading.name) }

                val countriesDtos = restCountriesRepo.getAll()
                val countries = countriesDtos.map { it.toCountry() }

                _uiState.update { it.copy(dataLoadingState = DataLoadingStates.Ready.name, countriesList = countries) }

            }catch (e: IOException){
                _uiState.update {
                    it.copy(
                        dataLoadingState = DataLoadingStates.Error.name,
                        errorMessage = e.message.toString()
                    )
                }
                Log.d("Error", e.message.toString())
            }catch (e: Exception){
                _uiState.update {
                    it.copy(
                        dataLoadingState = DataLoadingStates.Error.name,
                        errorMessage = e.message.toString()
                    )
                }
                Log.d("Error", e.message.toString())
            }

        }

    }

}
package com.example.restcountries.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restcountries.ui.screens.CountriesListScreen
import com.example.restcountries.ui.screens.CountryDetailsScreen
import com.example.restcountries.ui.screens.ErrorScreen
import com.example.restcountries.ui.screens.LoadingScreen
import com.example.restcountries.ui.theme.RestCountriesTheme
import com.example.restcountries.utils.DataLoadingStates
import com.example.restcountries.utils.Routes


@Composable
fun App(){

    val viewModel: CountriesVM = viewModel(factory = CountriesVM.Factory)
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()


    Surface(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().statusBarsPadding()
    ) {

        when(uiState.dataLoadingState){
            DataLoadingStates.Loading.name -> LoadingScreen()

            DataLoadingStates.Ready.name -> NavHost(
                navController = navController,
                startDestination = Routes.List.name,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ) {
                composable(route = Routes.List.name){
                    CountriesListScreen(list = uiState.countriesList, onClick = { i ->
                        viewModel.setSelectedCountry(i)
                        navController.navigate(Routes.Country.name)
                    })
                }

                composable(route = Routes.Country.name){
                    CountryDetailsScreen(country = uiState.selectedCountry)
                }
            }

            DataLoadingStates.Error.name -> ErrorScreen(message = uiState.errorMessage, onRetry = {
                viewModel.getCountries()
            })

        }
    }

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview(){
    RestCountriesTheme {
        App()
    }

}
package com.example.restcountries.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restcountries.R
import com.example.restcountries.ui.components.TopAppBar
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
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    val title = when (currentRoute) {
        Routes.List.name -> stringResource(R.string.app_name)
        Routes.Country.name -> uiState.selectedCountry?.name ?: ""
        else -> ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                canGoBack = navController.previousBackStackEntry != null,
                navUp = { navController.navigateUp() },
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
    ) {

        when(uiState.dataLoadingState){
            DataLoadingStates.Loading.name -> LoadingScreen()

            DataLoadingStates.Ready.name -> NavHost(
                navController = navController,
                startDestination = Routes.List.name,
                modifier = Modifier.padding(it)
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
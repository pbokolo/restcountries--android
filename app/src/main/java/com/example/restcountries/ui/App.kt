package com.example.restcountries.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restcountries.R
import com.example.restcountries.ui.components.TopAppBar
import com.example.restcountries.ui.screens.CountriesListScreen
import com.example.restcountries.ui.screens.CountryDetailsScreen
import com.example.restcountries.ui.theme.RestCountriesTheme
import androidx.compose.runtime.getValue
import com.example.restcountries.utils.Routes

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restcountries.ui.screens.ErrorScreen
import com.example.restcountries.ui.screens.LoadingScreen
import com.example.restcountries.utils.DataLoadingStates


@Composable
fun App(){

    // Creates an instance of the CountriesVM that's tied to the lifecycle of the app
    val viewModel: CountriesVM = viewModel()

    // Gets the uiState from the view model and observes it
    val uiState by viewModel.uiState.collectAsState()

    val navController: NavHostController = rememberNavController()

    // Observes the back stack entry as a state
    val backStackEntry by navController.currentBackStackEntryAsState()

    val title = if(backStackEntry?.destination?.route == Routes.List.name) stringResource(R.string.app_name) else "Country"

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                canGoBack = navController.previousBackStackEntry != null,
                navUp = { navController.navigateUp() }
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
                startDestination = Routes.List,
                modifier = Modifier.padding(it)
            ) {
                composable(route = Routes.List.name){
                    CountriesListScreen( onClick = {
                        navController.navigate(Routes.Country.name)
                    })
                }

                composable(route = Routes.Country.name){
                    CountryDetailsScreen()
                }
            }

            DataLoadingStates.Error.name -> ErrorScreen()

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
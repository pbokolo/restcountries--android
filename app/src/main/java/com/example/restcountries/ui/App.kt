package com.example.restcountries.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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

enum class Routes(){
    List, Country
}

@Composable
fun App(){

    val navController: NavHostController = rememberNavController()

    // Observes the back stack entry as a state
    val backStackEntry by navController.currentBackStackEntryAsState()

    val title = if(backStackEntry?.destination?.route == "List") stringResource(R.string.app_name) else "Country"

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

        NavHost(
            navController = navController,
            startDestination = Routes.List.name,
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

    }

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview(){
    RestCountriesTheme {
        App()
    }

}
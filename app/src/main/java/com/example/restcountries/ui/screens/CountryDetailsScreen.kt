package com.example.restcountries.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.restcountries.model.Country

@Composable
fun CountryDetailsScreen(country: Country?){
    Column(modifier = Modifier.fillMaxSize()) {
        if(country == null){
            Text(text = "No country selected")
        }else{
            Text(text = country.name)
        }
    }
}
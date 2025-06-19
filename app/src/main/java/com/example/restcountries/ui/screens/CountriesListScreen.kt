package com.example.restcountries.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.restcountries.model.Country

@Composable
fun CountriesListScreen(list: List<Country>, onClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "${list.size} countries retrieved")
        Button(onClick = onClick) {
            Text(text = "Next")
        }
    }
}
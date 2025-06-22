package com.example.restcountries.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restcountries.model.Country
import com.example.restcountries.ui.components.CountryCard

@Composable
fun CountriesListScreen(list: List<Country>, onClick: () -> Unit){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp)
    ) {
        if(!list.isEmpty()){
            itemsIndexed(list) { i, c -> CountryCard(country = c, onClick = onClick) }
        }
    }
}
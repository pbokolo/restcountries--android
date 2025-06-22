package com.example.restcountries.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restcountries.data.countries
import com.example.restcountries.model.Country
import com.example.restcountries.ui.components.CountryCard
import com.example.restcountries.ui.theme.RestCountriesTheme

@Composable
fun CountriesListScreen(list: List<Country>, onClick: () -> Unit){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        if(!list.isEmpty()){
            itemsIndexed(list) { i, c -> CountryCard(country = c, onClick = onClick) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CountriesListScreenPreview(){
    RestCountriesTheme {
        CountriesListScreen(list = countries, onClick = {})
    }
}
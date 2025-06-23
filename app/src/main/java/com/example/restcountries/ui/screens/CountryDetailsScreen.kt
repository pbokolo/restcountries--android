package com.example.restcountries.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restcountries.data.countries
import com.example.restcountries.model.Country
import com.example.restcountries.ui.components.AsyncImg
import com.example.restcountries.ui.theme.RestCountriesTheme

@Composable
fun CountryDetailsScreen(country: Country?) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (country == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) { }
            Text(text = "No country selected")
        } else {
            AsyncImg(
                url = country.flagUrl,
                description = country.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
                Text(
                    text = country.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

        }
    }
}


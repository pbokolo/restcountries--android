package com.example.restcountries.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restcountries.R
import com.example.restcountries.data.countries
import com.example.restcountries.model.Country
import com.example.restcountries.ui.theme.RestCountriesTheme
import com.example.restcountries.utils.formatPopulation

@Composable
fun CountryCard(country: Country, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.clickable {
            onClick()
        }.height(240.dp)
    ) {
        Column( modifier = Modifier.fillMaxSize()) {

            AsyncImg(
                url = country.flagUrl,
                description = country.name,
                modifier = Modifier.fillMaxWidth().weight(.45f)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(.55f).padding(8.dp)
            ) {
                Text(
                    text = country.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                CountryDetailText(text = country.capitalCity, icon = R.drawable.ic_city, textStyle = MaterialTheme.typography.bodySmall)
                CountryDetailText(text = country.region, icon = R.drawable.ic_region, textStyle = MaterialTheme.typography.bodySmall)
                CountryDetailText(text = formatPopulation(country.population), icon = R.drawable.ic_diversity, textStyle = MaterialTheme.typography.bodySmall)
                CountryDetailText(text = country.currency, icon = R.drawable.ic_currency, textStyle = MaterialTheme.typography.bodySmall)
            }
        }

    }
}

@Composable
fun CountryDetailText(text: String, @DrawableRes icon: Int, textStyle: TextStyle){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = textStyle
        )
    }
}

@Composable
@Preview
fun CountryCardPreview(){
    RestCountriesTheme {
        CountryCard(country = countries[0], onClick = {})
    }
}





package com.example.restcountries.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restcountries.data.countries
import com.example.restcountries.model.Country
import com.example.restcountries.ui.components.AsyncImg
import com.example.restcountries.ui.theme.RestCountriesTheme
import com.example.restcountries.R
import com.example.restcountries.ui.components.CountryDetailText

@Composable
fun CountryDetailsScreen(country: Country?, onBackClicked: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        if (country == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) { }
            Text(text = "No country selected")
        } else {
            Column {

                Box(modifier = Modifier
                    .fillMaxWidth()) {
                    AsyncImg(
                        url = country.flagUrl,
                        description = country.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(240.dp)
                    )

                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(48.dp)
                            .shadow(elevation = 6.dp, shape = CircleShape, clip = false)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .95f))
                            .align(Alignment.TopStart)
                            .clickable {
                                onBackClicked()
                            },
                        contentAlignment = Alignment.Center,

                        ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .animateContentSize(
                            animationSpec = tween(durationMillis = 500)
                        )
                ) {

                    var expanded by remember { mutableStateOf(false) }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable{
                            expanded = !expanded
                        }
                    ) {
                        Text(
                            text = country.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.weight(.8f)
                        )

                        val rotationAngle by animateFloatAsState(
                            targetValue = if(expanded) 180f else 0f,
                            label = "Icon rotation"
                        )

                        Box{
                            Icon(
                                painter = painterResource(R.drawable.ic_expand),
                                contentDescription = "Expand",
                                tint = MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.rotate(rotationAngle)
                            )
                        }
                    }

                    if(expanded) {
                        CountryDetailText(
                            text = country.capitalCity,
                            icon = R.drawable.ic_city,
                            textStyle = MaterialTheme.typography.bodyLarge
                        )
                        CountryDetailText(
                            text = country.region,
                            icon = R.drawable.ic_region,
                            textStyle = MaterialTheme.typography.bodyLarge
                        )
                        CountryDetailText(
                            text = country.population.toString(),
                            icon = R.drawable.ic_diversity,
                            textStyle = MaterialTheme.typography.bodyLarge
                        )
                        CountryDetailText(
                            text = country.currency,
                            icon = R.drawable.ic_currency,
                            textStyle = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Text(text = "Neighbors: 10", style = MaterialTheme.typography.bodySmall)

                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CountryDetailsScreenPreview() {
    RestCountriesTheme {
        CountryDetailsScreen(country = countries[0], onBackClicked = {})
    }
}

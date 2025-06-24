package com.example.restcountries.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun CountryDetailsScreen(country: Country?, onBackClicked: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        if (country == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) { }
            Text(text = "No country selected")
        } else {
            Column {


                Box(modifier = Modifier
                    .weight(.4f)
                    .fillMaxWidth()) {
                    AsyncImg(
                        url = country.flagUrl,
                        description = country.name,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(48.dp)
                            .shadow(elevation = 6.dp, shape = CircleShape, clip = false)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .85f))
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
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .weight(.6f)
                ) {
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
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CountryDetailsScreenPreview() {
    RestCountriesTheme {
        CountryDetailsScreen(country = countries[0], onBackClicked = {})
    }
}

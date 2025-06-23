package com.example.restcountries.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restcountries.R
import com.example.restcountries.ui.components.AnimatedGifImage
import com.example.restcountries.ui.theme.RestCountriesTheme

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {

            AnimatedGifImage(
                id = R.drawable.app_error,
                description = "Error",
                modifier = Modifier.size(120.dp)
            )

            Text(text = message)

            Button(onClick = { onRetry() }) { Text(text = "Retry") }
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ErrorScreenPreview(){
    RestCountriesTheme {
        ErrorScreen(message = "Timeout", onRetry = {})
    }
}

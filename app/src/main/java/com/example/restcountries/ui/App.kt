package com.example.restcountries.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.restcountries.ui.theme.RestCountriesTheme

@Composable
fun App(){
        Text(text = "On the way")

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview(){
    RestCountriesTheme {
        App()
    }

}
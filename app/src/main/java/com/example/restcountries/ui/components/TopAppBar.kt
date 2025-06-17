package com.example.restcountries.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.restcountries.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, canGoBack: Boolean, navUp: () -> Unit){
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
        if(canGoBack){
            IconButton(onClick = navUp) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Navigate up"
                )
            }
        }
    }
    )
}
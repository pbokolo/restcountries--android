package com.example.restcountries.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.restcountries.R

@Composable
fun AnimatedGifImage(@DrawableRes id: Int, description: String, modifier: Modifier){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(id) // Your GIF
            .decoderFactory(GifDecoder.Factory())
            .build(),
        contentDescription = description,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
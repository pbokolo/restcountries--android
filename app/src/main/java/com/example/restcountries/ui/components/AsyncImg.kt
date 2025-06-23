package com.example.restcountries.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.restcountries.R

@Composable
fun AsyncImg(url: String, description: String, modifier: Modifier){

    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .placeholder(R.drawable.loading) // Note: GIF won't animate here
            .error(R.drawable.error)
            .build(),
        imageLoader = ImageLoader.Builder(context)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
    )

    Box(modifier = modifier){
        if (painter.state is AsyncImagePainter.State.Loading) {

            AnimatedGifImage(
                id = R.drawable.loading,
                description = "Loading...",
                modifier = Modifier.fillMaxSize()
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(url)
                .crossfade(true)
                .error(R.drawable.error)
                .build(),
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }

}
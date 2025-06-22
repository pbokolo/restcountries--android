package com.example.restcountries.utils

import java.text.DecimalFormat

fun formatPopulation(population: Long): String {
    val formatter = DecimalFormat("#.#") // keeps 1 decimal max, like 1.2M

    return when {
        population >= 1_000_000_000 -> "${formatter.format(population / 1_000_000_000.0)}Md"
        population >= 1_000_000 -> "${formatter.format(population / 1_000_000.0)}M"
        population >= 1_000 -> "${formatter.format(population / 1_000.0)}K"
        else -> population.toString()
    }
}
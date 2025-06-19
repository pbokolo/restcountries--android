package com.example.restcountries

import android.app.Application
import com.example.restcountries.data.AppContainer
import com.example.restcountries.data.DefaultAppContainer

class RestCountriesApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}
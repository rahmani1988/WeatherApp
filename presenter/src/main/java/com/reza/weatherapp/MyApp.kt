package com.reza.weatherapp

import android.app.Application
import com.reza.weatherapp.di.AppComponent
import com.reza.weatherapp.di.DaggerAppComponent

open class MyApp: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
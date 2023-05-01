package com.reza.weatherapp

import android.app.Application
import com.reza.weatherapp.di.AppComponent
import com.reza.weatherapp.di.DaggerAppComponent

open class MyApp: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Test comment
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
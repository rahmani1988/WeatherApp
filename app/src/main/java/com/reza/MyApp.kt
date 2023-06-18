package com.reza

import android.app.Application
import com.reza.di.AppComponent
import com.reza.di.DaggerAppComponent

open class MyApp: Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
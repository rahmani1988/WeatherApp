package com.reza.app

import android.app.Application
import com.reza.app.di.AppComponent
import com.reza.auth.ui.start.StartComponent
import com.reza.auth.ui.start.StartComponentProvider
import com.reza.app.di.DaggerAppComponent

open class MyApp: Application(), StartComponentProvider {

    private val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideStartComponent(): StartComponent {
        return appComponent.startComponent().create()
    }
}
package com.reza.app

import android.app.Application
import com.reza.app.di.AppComponent
import com.reza.start.ui.start.StartComponent
import com.reza.start.ui.start.StartComponentProvider
import com.reza.app.di.DaggerAppComponent
import com.reza.auth.ui.AuthComponent
import com.reza.auth.ui.AuthComponentProvider

open class MyApp: Application(), StartComponentProvider, AuthComponentProvider {

    private val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun provideStartComponent(): StartComponent {
        return appComponent.startComponent().create()
    }

    override fun provideAuthComponent(): AuthComponent {
        return appComponent.authComponent().create()
    }
}
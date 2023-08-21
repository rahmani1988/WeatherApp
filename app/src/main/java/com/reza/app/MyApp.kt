package com.reza.app

import android.app.Application
import com.google.firebase.appcheck.ktx.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
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
    override fun onCreate() {
        super.onCreate()
        initializeAppCheck()
    }

    private fun initializeAppCheck() {
        Firebase.initialize(context = this)
        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance(),
        )
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
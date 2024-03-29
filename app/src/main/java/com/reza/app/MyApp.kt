package com.reza.app

import android.app.Application
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.ktx.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.reza.app.di.AppComponent
import com.reza.start.ui.start.StartComponent
import com.reza.start.ui.start.StartComponentProvider
import com.reza.app.di.DaggerAppComponent
import com.reza.auth.ui.AuthComponent
import com.reza.auth.ui.AuthComponentProvider
import com.reza.home.ui.HomeComponent
import com.reza.home.ui.HomeComponentProvider

open class MyApp : Application(),
    StartComponentProvider,
    AuthComponentProvider,
    HomeComponentProvider {

    private val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        initializeAppCheck()
    }

    private fun initializeAppCheck() {
        Firebase.initialize(context = this)
        // To get a token for testing purposes in debug mode
        if (BuildConfig.DEBUG) {
            Firebase.appCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance(),
            )
        } else {
            Firebase.appCheck.installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance(),
            )
        }
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

    override fun provideHomeComponent(): HomeComponent {
        return appComponent.homeComponent().create()
    }
}
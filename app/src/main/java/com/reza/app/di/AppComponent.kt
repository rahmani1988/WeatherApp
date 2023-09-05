package com.reza.app.di

import android.content.Context
import com.reza.auth.ui.AuthComponent
import com.reza.core.di.ThreadingModule
import com.reza.home.ui.HomeComponent
import com.reza.start.ui.start.StartComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SubcomponentsModule::class,
        ThreadingModule::class,
        PresenterModule::class,
        AnalyticsModule::class,
        UserManagerModule::class,
        DataSourceModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun startComponent(): StartComponent.Factory
    fun authComponent(): AuthComponent.Factory
    fun homeComponent(): HomeComponent.Factory
}
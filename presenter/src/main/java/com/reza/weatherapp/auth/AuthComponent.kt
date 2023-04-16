package com.reza.weatherapp.auth

import com.reza.weatherapp.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface AuthComponent {
    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthComponent
    }
}
package com.reza.weatherapp.home

import com.reza.weatherapp.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {
    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }
}
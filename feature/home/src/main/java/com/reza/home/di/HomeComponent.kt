package com.reza.home.di

import com.reza.core.di.ActivityScope
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
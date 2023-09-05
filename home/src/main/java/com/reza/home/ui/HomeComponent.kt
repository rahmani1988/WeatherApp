package com.reza.home.ui

import com.reza.core.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)
}
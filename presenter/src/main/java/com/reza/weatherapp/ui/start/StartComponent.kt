package com.reza.weatherapp.ui.start

import com.reza.weatherapp.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface StartComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): StartComponent
    }

    fun inject(activity: StartActivity)
}
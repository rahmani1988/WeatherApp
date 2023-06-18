package com.reza.ui.start

import com.reza.di.ActivityScope
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
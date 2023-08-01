package com.reza.auth.ui

import com.reza.core.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : AuthComponent
    }

    fun inject(activity: AuthActivity)
}
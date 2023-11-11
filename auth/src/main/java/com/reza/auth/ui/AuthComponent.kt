package com.reza.auth.ui

import com.reza.auth.ui.login.LoginFragment
import com.reza.auth.ui.register.RegisterFragment
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
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
}
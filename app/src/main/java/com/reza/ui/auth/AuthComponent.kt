package com.reza.ui.auth



import com.reza.di.ActivityScope
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
package com.reza.app.di

import com.reza.app.ui.auth.AuthComponent
import com.reza.app.ui.home.HomeComponent
import com.reza.auth.ui.start.StartComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthComponent::class,
        HomeComponent::class,
        StartComponent::class
    ]
)
class AppSubcomponents
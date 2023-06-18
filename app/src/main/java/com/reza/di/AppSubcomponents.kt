package com.reza.di

import com.reza.ui.auth.AuthComponent
import com.reza.ui.home.HomeComponent
import com.reza.ui.start.StartComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthComponent::class,
        HomeComponent::class,
        StartComponent::class
    ]
)
class AppSubcomponents
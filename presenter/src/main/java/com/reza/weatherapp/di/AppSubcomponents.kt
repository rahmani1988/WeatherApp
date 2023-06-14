package com.reza.weatherapp.di

import com.reza.weatherapp.ui.auth.AuthComponent
import com.reza.weatherapp.ui.home.HomeComponent
import com.reza.weatherapp.ui.start.StartComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthComponent::class,
        HomeComponent::class,
        StartComponent::class
    ]
)
class AppSubcomponents
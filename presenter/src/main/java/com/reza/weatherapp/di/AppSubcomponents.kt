package com.reza.weatherapp.di

import com.reza.weatherapp.auth.AuthComponent
import com.reza.weatherapp.ui.home.HomeComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthComponent::class,
        HomeComponent::class
    ]
)
class AppSubcomponents
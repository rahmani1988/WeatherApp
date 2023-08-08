package com.reza.app.di

import com.reza.auth.ui.AuthComponent
import com.reza.home.di.HomeComponent
import com.reza.start.ui.start.StartComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthComponent::class,
        HomeComponent::class,
        StartComponent::class
    ]
)
class SubcomponentsModule
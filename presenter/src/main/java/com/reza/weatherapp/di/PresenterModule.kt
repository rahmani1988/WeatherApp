package com.reza.weatherapp.di

import com.reza.weatherapp.ui.start.StartContract
import com.reza.weatherapp.ui.start.StartPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindStartPresenter(startPresenter: StartPresenter): StartContract.Presenter
}
package com.reza.di

import com.reza.ui.start.StartContract
import com.reza.ui.start.StartPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindStartPresenter(startPresenter: StartPresenter): StartContract.Presenter
}
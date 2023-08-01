package com.reza.app.di


import com.reza.start.ui.start.StartContract
import com.reza.start.ui.start.StartPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindStartPresenter(startPresenter: StartPresenter): StartContract.Presenter
}
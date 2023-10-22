package com.reza.app.di


import com.reza.home.ui.HomeContract
import com.reza.home.ui.HomePresenter
import com.reza.start.ui.start.StartContract
import com.reza.start.ui.start.StartPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindStartPresenter(startPresenter: StartPresenter): StartContract.Presenter

    @Binds
    abstract fun bindHomePresenter(homePresenter: HomePresenter): HomeContract.Presenter
}
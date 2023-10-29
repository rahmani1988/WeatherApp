package com.reza.app.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
object RxModule {
    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()
}
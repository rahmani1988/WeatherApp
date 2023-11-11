package com.reza.core.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object ThreadingModule {

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    @IoSchedulers
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @ComputationSchedulers
    fun provideComputationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @Singleton
    @MainSchedulers
    fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoSchedulers

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainSchedulers

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ComputationSchedulers
package com.reza.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object ThreadingModule {
    @Provides
    @Singleton
    @IoSchedulers
    fun provideIoScheduler(): Scheduler = Schedulers.io()

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
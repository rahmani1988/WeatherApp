package com.reza.app.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAnalytics() = Firebase.analytics
}
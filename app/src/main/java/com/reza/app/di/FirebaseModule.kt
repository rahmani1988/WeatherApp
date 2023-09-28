package com.reza.app.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth
}
package com.reza.core.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.reza.core.BuildConfig

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CoreModule {
    @Provides
    @Singleton
    fun provideGoogleSingInClient(context: Context): GoogleSignInClient {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_SING_IN_WEB_CLIENT_ID)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, options)
    }
}
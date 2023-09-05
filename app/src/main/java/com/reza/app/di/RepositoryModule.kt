package com.reza.app.di

import com.reza.auth.data.repository.AuthRepository
import com.reza.auth.data.repository.DefaultAuthRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(defaultAuthRepository: DefaultAuthRepository): AuthRepository
}
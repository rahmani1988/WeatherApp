package com.reza.app.di

import com.reza.auth.data.datasource.remote.AuthRemoteDataSource
import com.reza.auth.data.datasource.remote.DefaultAuthRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindAuthDataSource(defaultAuthDataSource: DefaultAuthRemoteDataSource): AuthRemoteDataSource
}
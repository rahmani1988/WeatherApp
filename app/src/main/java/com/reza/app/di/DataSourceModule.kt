package com.reza.app.di

import com.reza.auth.data.datasource.AuthDataSource
import com.reza.auth.data.datasource.DefaultAuthDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindAuthDataSource(defaultAuthDataSource: DefaultAuthDataSource): AuthDataSource
}
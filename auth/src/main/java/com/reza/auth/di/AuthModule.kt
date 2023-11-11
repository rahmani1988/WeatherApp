package com.reza.auth.di

import com.reza.auth.ui.login.LoginContract
import com.reza.auth.ui.login.LoginPresenter
import com.reza.auth.ui.register.RegisterContract
import com.reza.auth.ui.register.RegisterPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class AuthModule {
    @Binds
    abstract fun bindLoginPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

    @Binds
    abstract fun bindRegisterPresenter(registerPresenter: RegisterPresenter): RegisterContract.Presenter
}
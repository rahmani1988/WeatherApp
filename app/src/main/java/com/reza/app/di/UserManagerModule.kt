package com.reza.app.di


import com.reza.core.util.user.DefaultUserManager
import com.reza.core.util.user.UserManager
import dagger.Binds
import dagger.Module

@Module
abstract class UserManagerModule {

    @Binds
    abstract fun bindUserManager(defaultUserManager: DefaultUserManager): UserManager
}
package com.reza.app.di

import android.os.UserManager
import com.reza.core.util.user.DefaultUserManager
import dagger.Binds
import dagger.Module

@Module
abstract class UserManagerModule {

    @Binds
    abstract fun bindUserManager(defaultUserManager: DefaultUserManager): UserManager
}
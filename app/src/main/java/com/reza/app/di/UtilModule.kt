package com.reza.app.di

import com.reza.core.util.string.DefaultStringResolver
import com.reza.core.util.string.StringResolver
import com.reza.core.util.validation.DefaultValidator
import com.reza.core.util.validation.Validator
import dagger.Binds
import dagger.Module

@Module
abstract class UtilModule {
    @Binds
    abstract fun bindStringResolver(defaultStringResolver: DefaultStringResolver): StringResolver

    @Binds
    abstract fun bindValidator(defaultValidator: DefaultValidator): Validator
}
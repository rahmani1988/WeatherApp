package com.reza.di

import com.reza.util.analytics.AnalyticsHelper
import com.reza.util.analytics.DefaultAnalyticsHelper
import dagger.Binds
import dagger.Module

@Module
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticHelper(defaultAnalyticsHelper: DefaultAnalyticsHelper): AnalyticsHelper
}
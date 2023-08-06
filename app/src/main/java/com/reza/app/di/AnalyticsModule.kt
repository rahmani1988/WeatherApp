package com.reza.app.di


import com.reza.core.util.analytics.AnalyticsHelper
import com.reza.core.util.analytics.DefaultAnalyticsHelper
import dagger.Binds
import dagger.Module

@Module
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticHelper(defaultAnalyticsHelper: DefaultAnalyticsHelper): AnalyticsHelper
}
package com.reza.weatherapp.di

import com.reza.weatherapp.util.analytics.AnalyticsHelper
import com.reza.weatherapp.util.analytics.DefaultAnalyticsHelper
import dagger.Binds
import dagger.Module

@Module
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticHelper(defaultAnalyticsHelper: DefaultAnalyticsHelper): AnalyticsHelper
}
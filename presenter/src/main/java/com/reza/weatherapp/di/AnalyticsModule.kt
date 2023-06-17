package com.reza.weatherapp.di

import com.reza.weatherapp.util.analytics.AnalyticsHelper
import com.reza.weatherapp.util.analytics.AnalyticsHelperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticHelper(analyticsHelper: AnalyticsHelper): AnalyticsHelperImpl
}
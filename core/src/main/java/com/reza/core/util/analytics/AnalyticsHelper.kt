package com.reza.core.util.analytics

import com.reza.core.models.local.analytics.Analytics

interface AnalyticsHelper {
    fun logEvent(event: Analytics.Event, vararg params: Analytics.ParamData)
}
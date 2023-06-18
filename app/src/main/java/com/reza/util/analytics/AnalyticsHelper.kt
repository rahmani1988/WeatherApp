package com.reza.util.analytics

import com.reza.models.analytics.Event
import com.reza.models.analytics.ParamData

interface AnalyticsHelper {
    fun logEvent(event: Event, vararg params: ParamData)
}
package com.reza.core.util.analytics

import com.reza.core.models.local.analytics.Event
import com.reza.core.models.local.analytics.ParamData

interface AnalyticsHelper {
    fun logEvent(event: Event, vararg params: ParamData)
}
package com.reza.core.util.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.reza.core.models.local.analytics.Event
import com.reza.core.models.local.analytics.FirebaseParamDate
import com.reza.core.models.local.analytics.ParamData

interface AnalyticsHelper {
    fun logCustomEvent(event: Event, vararg params: ParamData)
    fun logEvent(event: String, vararg params: FirebaseParamDate)
}
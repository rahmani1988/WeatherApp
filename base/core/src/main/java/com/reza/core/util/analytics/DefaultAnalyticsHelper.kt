package com.reza.core.util.analytics

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.reza.core.models.local.analytics.Analytics
import javax.inject.Inject

class DefaultAnalyticsHelper @Inject constructor() : AnalyticsHelper {

    private val firebaseAnalytics = Firebase.analytics
    override fun logEvent(event: Analytics.Event, vararg params: Analytics.ParamData) {
        firebaseAnalytics.logEvent(event.eventName) {
            params.forEach {
                param(it.param.paramName, it.value)
            }
        }
    }
}
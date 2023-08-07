package com.reza.core.util.analytics

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.reza.core.models.local.analytics.Event
import com.reza.core.models.local.analytics.ParamData
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject


class DefaultAnalyticsHelper @Inject constructor(): AnalyticsHelper {

    private val firebaseAnalytics = Firebase.analytics

    override fun logEvent(event: Event, vararg params: ParamData) {
        firebaseAnalytics.logEvent(event.eventName) {
            params.forEach {
                param(it.param.paramName, it.value)
            }
        }
    }
}
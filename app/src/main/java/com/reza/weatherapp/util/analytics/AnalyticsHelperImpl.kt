package com.reza.weatherapp.util.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.reza.models.analytics.Event
import com.reza.models.analytics.ParamData

class DefaultAnalyticsHelper : AnalyticsHelper {

    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    override fun logEvent(event: Event, vararg params: ParamData) {
        firebaseAnalytics.logEvent(event.eventName) {
            params.forEach {
                param(it.param.paramName, it.value)
            }
        }
    }
}
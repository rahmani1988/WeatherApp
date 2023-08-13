package com.reza.core.util.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.reza.core.models.local.analytics.Event
import com.reza.core.models.local.analytics.ParamData
import com.google.firebase.analytics.ktx.logEvent
import com.reza.core.models.local.analytics.FirebaseParamDate
import javax.inject.Inject


class DefaultAnalyticsHelper @Inject constructor() : AnalyticsHelper {

    private val firebaseAnalytics = Firebase.analytics

    private val defaultParameters = Bundle().apply {
        putString("test", "reza")
        putInt("level_difficulty", 0)
    }

    init {
        // to add default parameters to events. If any parameter is added to an event, it will be replaced by default ones
        firebaseAnalytics.setDefaultEventParameters(defaultParameters)
    }

    override fun logCustomEvent(event: Event, vararg params: ParamData) {
        firebaseAnalytics.logEvent(event.eventName) {
            params.forEach {
                param(it.param.paramName, it.value)
            }
        }
    }

    override fun logEvent(event: String, vararg params: FirebaseParamDate) {
        firebaseAnalytics.logEvent(event) {
            params.forEach {
                param(it.param, it.value)
            }
        }
    }
}
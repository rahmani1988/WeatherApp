package com.reza.core.models.local.analytics

import com.google.firebase.analytics.FirebaseAnalytics

class Analytics {
    data class ParamData(
        val param: Param,
        val value: String
    )

    enum class Param(val paramName: String) {
        /**
         * These events are added from [FirebaseAnalytics.Param.javaClass]
         */
        SCREEN_NAME("screen_name"),
        SCREEN_CLASS("screen_class"),

        /**
         * Custom Params are added here
         */
        TEST_PARAM("test_param")
    }

    enum class Event(val eventName: String) {
        /**
         * These events are added from [FirebaseAnalytics.Event.javaClass]
         */
        SCREEN_VIEW("screen_view"),

        /**
         * Custom events are added here
         */
        EVENT_TEST("event_test")
    }
}
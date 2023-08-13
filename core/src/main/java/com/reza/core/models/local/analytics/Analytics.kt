package com.reza.core.models.local.analytics

import com.google.firebase.analytics.FirebaseAnalytics

data class ParamData(
    val param: Param,
    val value: String
)

data class FirebaseParamDate(
    val param: String,
    val value: String
)

enum class Param(val paramName: String) {
    SCREEN_NAME("screen_name"),
    SCREEN_CLASS("screen_class"),
    PARAM_TEST("param_test")
}

enum class Event(val eventName: String) {
    SCREEN_VIEW("screen_view"),
    APP_OPEN("app_open"),
    TEST("test")
}
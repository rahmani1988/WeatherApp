package com.reza.core.models.local.analytics

data class ParamData(
    val param: Param,
    val value: String
)

enum class Param(val paramName: String) {
    SCREEN_NAME("screen_name"),
    SCREEN_CLASS("screen_class")
}

enum class Event(val eventName: String) {
    SCREEN_VIEW("screen_view"),
    APP_OPEN("app_open")
}
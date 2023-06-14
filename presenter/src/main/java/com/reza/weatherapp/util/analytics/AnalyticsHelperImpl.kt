package com.reza.weatherapp.util.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

private const val ERROR_MESSAGE_FAILED_TO_INIT = "AnalyticsHelper must be first initialised by calling AnalyticsHelper.configure()"

class AnalyticsHelperImpl: AnalyticsHelper {

    private val TAG = AnalyticsHelperImpl::class.java.simpleName

    /**
     * Event name constants
     */
    object Event {
        const val SCREEN_VIEW = "screen_view"
        const val APP_OPEN = "app_open"
        const val APP_CLOSE = "app_close"
        const val APP_UPDATE = "app_update_android"  // for use with Universal Analytics
        const val VALIDATION_ERROR = "error_validation"
    }

    /**
     * Event parameter name constants
     */
    object Param {
        const val SCREEN_NAME = "screen_name"
        const val SCREEN_CLASS = "screen_class"
        const val TIMESTAMP = "timestamp"
        const val ERROR_MESSAGE = "error_message"
        const val PREVIOUS_APP_VERSION = "previous_app_version"
        const val ENGAGEMENT_TIME = "engagement_time"
    }

    /**
     * User property name constants
     */
    object UserProperty {
        const val ENVIRONMENT = "environment"
        const val APP_INSTANCE_ID = "app_instance_id"
        const val TIMEZONE_OFFSET = "timezone_offset"
    }


    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @Synchronized
    override fun config() {
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics
    }



}
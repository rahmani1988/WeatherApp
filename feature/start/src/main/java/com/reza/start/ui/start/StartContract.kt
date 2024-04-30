package com.reza.start.ui.start

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface StartContract {
    interface View : BaseView {
        fun navigateToAuth()
        fun navigateToHome()
    }

    interface Presenter : BasePresenter<View> {
        fun getUser()
        fun sendAnalyticsEvent()
    }
}
package com.reza.start.ui.start

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface StartContract {
    interface View : BaseView {
        fun showLoader()
        fun hideLoader()
        fun navigateToAuth()
        fun navigateToDashboard()
        fun showError(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getUserStatus()
    }
}
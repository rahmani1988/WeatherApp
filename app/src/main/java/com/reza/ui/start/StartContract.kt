package com.reza.ui.start

import com.reza.ui.base.BasePresenter
import com.reza.ui.base.BaseView

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
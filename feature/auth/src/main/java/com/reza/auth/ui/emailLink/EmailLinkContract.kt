package com.reza.auth.ui.emailLink

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface EmailLinkContract {
    interface View: BaseView {
        fun showErrorMessage(error: String)
        fun showLoader()
        fun hideLoader()
        fun validateInputs(isValid: Boolean)
        fun navigateToHome()
    }

    interface Presenter: BasePresenter<View> {
        fun loginUserWithEmailLink(email: String)
        fun validateEmail(email: String)
    }

    interface EmailLinkClickHandler {
        fun onBackClicked()
    }
}
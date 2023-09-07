package com.reza.auth.ui

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface AuthContract {
    interface View : BaseView {
        fun navigateToHome()
        fun showErrorMessage(error: String)
        fun showLoader()
        fun hideLoader()
        fun validateInputs(isValid: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun createUserWithEmailAndPassword(email: String, password: String)
        fun validateEmail(email: String)
        fun validatePassword(password: String)
    }
}
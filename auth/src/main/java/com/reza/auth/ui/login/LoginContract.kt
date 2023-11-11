package com.reza.auth.ui.login

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface LoginContract {
    interface View: BaseView {
        fun showErrorMessage(error: String)
        fun showLoader()
        fun hideLoader()
        fun validateInputs(isValid: Boolean)
        fun navigateToHome()
    }

    interface Presenter: BasePresenter<View> {
        fun loginUserWithEmailAndPassword(email: String, password: String)
        fun validateEmail(email: String)
        fun validatePassword(password: String)
    }
}
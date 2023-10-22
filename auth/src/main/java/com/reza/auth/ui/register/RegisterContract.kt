package com.reza.auth.ui.register

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface RegisterContract {
    interface View: BaseView {
        fun showErrorMessage(error: String)
        fun showLoader()
        fun hideLoader()
        fun validateInputs(isValid: Boolean)
    }

    interface Presenter: BasePresenter<View> {

    }
}
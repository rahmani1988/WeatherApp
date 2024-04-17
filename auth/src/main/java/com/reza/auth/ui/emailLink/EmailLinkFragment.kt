package com.reza.auth.ui.emailLink

import com.reza.auth.databinding.FragmentEmailLinkBinding
import com.reza.auth.ui.LoginEmailLinkClickHandler
import com.reza.core.ui.base.BaseFragment

class EmailLinkFragment(private val loginEmailLinkClickHandler: LoginEmailLinkClickHandler) :
    BaseFragment<FragmentEmailLinkBinding>(), EmailLinkContract.View {

    override fun showErrorMessage(error: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun validateInputs(isValid: Boolean) {

    }

    override fun navigateToHome() {

    }

    override fun setupUi() {

    }

    override fun registerView() {

    }

    override fun setupListeners() {

    }

    override fun getViewBinding(): FragmentEmailLinkBinding {
        return FragmentEmailLinkBinding.inflate(layoutInflater)
    }
}
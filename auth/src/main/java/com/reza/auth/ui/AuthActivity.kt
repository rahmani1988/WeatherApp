package com.reza.auth.ui

import android.content.Intent
import com.reza.auth.databinding.ActivityAuthBinding
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.constant.HOME_ACTIVITY

class AuthActivity : BaseActivity<ActivityAuthBinding>(), AuthContract.View {

    override fun getViewBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun registerView() {

    }

    override fun setupUi() {

    }

    override fun setupSubscribers() {

    }

    override fun setupListeners() {

    }

    override fun navigateToHome() {
        val intent = Intent().apply {
            setClassName(this@AuthActivity, HOME_ACTIVITY)
        }
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage(error: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun validateInputs(isValid: Boolean) {
        // TODO should enable sign up button
    }
}
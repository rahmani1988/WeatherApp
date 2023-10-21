package com.reza.auth.ui

import android.content.Intent
import com.reza.auth.databinding.ActivityAuthBinding
import com.reza.auth.ui.login.LoginFragment
import com.reza.auth.ui.register.RegisterFragment
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.constant.Constant
import com.reza.core.util.extensions.addFragment
import com.reza.core.util.extensions.replaceFragment

class AuthActivity : BaseActivity<ActivityAuthBinding>(), AuthContract.View {

    override fun getViewBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun registerView() {

    }

    override fun setupUi() {
        addFragment(LoginFragment(), binding.frameLayoutAuth.id, false)
    }

    fun navigateToRegister() {
        replaceFragment(RegisterFragment(), binding.frameLayoutAuth.id, true)
    }

    override fun setupSubscribers() {

    }

    override fun setupListeners() {

    }

    override fun navigateToHome() {
        val intent = Intent().apply {
            setClassName(this@AuthActivity, Constant.Activities.HOME_ACTIVITY.path)
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
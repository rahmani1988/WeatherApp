package com.reza.auth.ui

import android.content.Intent
import android.os.Bundle
import com.reza.auth.databinding.ActivityAuthBinding
import com.reza.auth.ui.emailLink.EmailLinkContract
import com.reza.auth.ui.emailLink.EmailLinkFragment
import com.reza.auth.ui.login.LoginContract
import com.reza.auth.ui.login.LoginFragment
import com.reza.auth.ui.register.RegisterFragment
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.constant.Constant
import com.reza.core.util.extensions.addFragment
import com.reza.core.util.extensions.popBackStack
import com.reza.core.util.extensions.replaceFragment

class AuthActivity : BaseActivity<ActivityAuthBinding>(), LoginContract.LoginFragmentClickHandler,
    EmailLinkContract.EmailLinkClickHandler {

    lateinit var authComponent: AuthComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        // To initialise authComponent
        authComponent = (applicationContext as AuthComponentProvider).provideAuthComponent()
        super.onCreate(savedInstanceState)
    }

    override fun getViewBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun registerView() {

    }

    override fun setupUi() {
        addFragment(LoginFragment.newInstance(), binding.frameLayoutAuth.id, false)
    }

    private fun navigateToRegister() {
        replaceFragment(RegisterFragment(), binding.frameLayoutAuth.id, true)
    }

    private fun navigateToRegisterWithEmailLink() {
        replaceFragment(EmailLinkFragment(), binding.frameLayoutAuth.id, true)
    }

    fun navigateUp() {
        popBackStack()
    }

    override fun setupSubscribers() {

    }

    override fun setupListeners() {

    }

    fun navigateToHome() {
        val intent = Intent().apply {
            setClassName(this@AuthActivity, Constant.Activities.HOME_ACTIVITY.path)
        }
        startActivity(intent)
        finish()
    }

    override fun onEmailLinkClicked() {
        navigateToRegisterWithEmailLink()
    }

    override fun onNavigateToRegister() {
        navigateToRegister()
    }

    override fun onBackClicked() {
        popBackStack()
    }
}
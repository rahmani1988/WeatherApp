package com.reza.auth.ui.login

import com.reza.auth.databinding.FragmentLoginBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun setupUi() {

    }

    override fun registerView() {

    }

    override fun setupListeners() {
        binding.apply {
            imgEmail.setOnClickListener {
                (requireActivity() as AuthActivity).navigateToRegister()
            }
        }
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }
}
package com.reza.auth.ui.register

import com.reza.auth.databinding.FragmentRegisterBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun setupUi() {

    }

    override fun registerView() {

    }

    override fun setupListeners() {
        binding.apply {
            imgBack.setOnClickListener {
                (requireActivity() as? AuthActivity)?.popBackStack()
            }
        }
    }

    override fun getViewBinding(): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }
}
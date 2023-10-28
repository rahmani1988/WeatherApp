package com.reza.auth.ui.register

import androidx.core.widget.doAfterTextChanged
import com.jakewharton.rxbinding4.view.clicks
import com.reza.auth.databinding.FragmentRegisterBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    val compositeDisposable = CompositeDisposable()
    override fun setupUi() {

    }

    override fun registerView() {

    }

    override fun setupListeners() {
        binding.apply {

            imgBack.clicks().subscribe {  }.addTo()

            imgBack.setOnClickListener {
                (requireActivity() as? AuthActivity)?.popBackStack()
            }

            edtEmail.doAfterTextChanged {

            }

            edtPassword.doAfterTextChanged {

            }
        }
    }

    override fun getViewBinding(): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }
}
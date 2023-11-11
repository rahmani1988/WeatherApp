package com.reza.auth.ui.login

import android.content.Context
import com.jakewharton.rxbinding4.view.clicks
import com.reza.auth.databinding.FragmentLoginBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val DEBOUNCING_TIME = 300L

class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginContract.View {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var loginPresenter: LoginContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? AuthActivity)?.authComponent?.inject(this)
    }

    override fun setupUi() {

    }

    override fun registerView() {
        loginPresenter.attachView(this)
    }

    override fun setupListeners() {
        binding.apply {
            imgEmail.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    (requireActivity() as? AuthActivity)?.navigateToRegister()
                }.addTo(compositeDisposable)
        }
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

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
}
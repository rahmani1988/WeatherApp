package com.reza.auth.ui.login

import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.reza.auth.databinding.FragmentLoginBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
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
        /* NO-OP */
    }

    override fun registerView() {
        loginPresenter.attachView(this)
    }

    override fun setupListeners() {
        binding.apply {
            // buttons
            imgEmail.clicks()
                //.debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    (requireActivity() as? AuthActivity)?.navigateToRegister()
                }.addTo(compositeDisposable)

            // handling clicks on login button
            btnLogin.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    loginPresenter.loginUserWithEmailAndPassword(
                        email = edtEmail.text.toString().trim(),
                        password = edtPassword.text.toString().trim()
                    )
                }.addTo(compositeDisposable)

            // text fields
            edtEmail.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    loginPresenter.validateEmail(it.toString())
                }.addTo(compositeDisposable)

            edtPassword.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    loginPresenter.validatePassword(it.toString())
                }.addTo(compositeDisposable)
        }
    }

    override fun getViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun showErrorMessage(error: String) {
        Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        // TODO: hide text on login button
        // TODO: show loader on login button
    }

    override fun hideLoader() {
        // TODO: hide loader on login button
        // TODO: show text on login button
    }

    override fun validateInputs(isValid: Boolean) {
        binding.btnLogin.isEnabled = isValid
    }

    override fun navigateToHome() {
        (requireActivity() as? AuthActivity)?.navigateToHome()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginPresenter.detachView(this)
        loginPresenter.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
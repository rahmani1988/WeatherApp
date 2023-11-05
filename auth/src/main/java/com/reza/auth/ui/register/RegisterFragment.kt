package com.reza.auth.ui.register

import android.content.Context
import android.util.Log
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.reza.auth.databinding.FragmentRegisterBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.withLatestFrom
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val DEBOUNCING_TIME = 300L

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterContract.View {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var registerPresenter: RegisterContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? AuthActivity)?.authComponent?.inject(this)
    }

    override fun setupUi() {

    }

    override fun registerView() {
        registerPresenter.attachView(this)
    }

    override fun setupListeners() {
        binding.apply {
            // handling clicks on back button
            imgBack.clicks().debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS).subscribe {
                (requireActivity() as? AuthActivity)?.popBackStack()
            }.addTo(compositeDisposable)

            // getting email
            edtEmail.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    registerPresenter.validateEmail(it.toString())
                }.addTo(compositeDisposable)

            // getting password
            edtPassword.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    registerPresenter.validatePassword(it.toString())
                }.addTo(compositeDisposable)

            // handling clicks on login button
            btnLogin.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    registerPresenter.createUserWithEmailAndPassword(
                        email = edtEmail.text.toString().trim(),
                        password = edtPassword.text.toString().trim()
                    )
                }.addTo(compositeDisposable)
        }
    }

    override fun getViewBinding(): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun showErrorMessage(error: String) {

    }

    override fun showLoader() {
        // TODO: show loader on login button and disable that
    }

    override fun hideLoader() {
        // TODO: hide loader on login button and enable that
    }

    override fun validateInputs(isValid: Boolean) {
        binding.btnLogin.isEnabled = isValid
    }

    override fun navigateToHome() {
        (requireActivity() as? AuthActivity)?.navigateToHome()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
        registerPresenter.detachView(this)
        registerPresenter.destroy()
    }
}
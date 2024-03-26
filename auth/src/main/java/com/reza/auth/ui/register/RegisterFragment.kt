package com.reza.auth.ui.register

import android.content.Context
import android.util.Log
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
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
        /* NO-OP */
    }

    override fun registerView() {
        registerPresenter.attachView(this)
    }

    override fun setupListeners() {
        binding.apply {
            // buttons
            imgBack.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    (requireActivity() as? AuthActivity)?.popBackStack()
                }.addTo(compositeDisposable)

            btnRegister.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    registerPresenter.createUserWithEmailAndPassword(
                        email = edtEmail.text.toString().trim(),
                        password = edtPassword.text.toString().trim()
                    )
                }.addTo(compositeDisposable)

            // text fields
            edtEmail.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    registerPresenter.validateEmail(it.toString())
                }.addTo(compositeDisposable)

            edtPassword.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    registerPresenter.validatePassword(it.toString())
                }.addTo(compositeDisposable)
        }
    }

    override fun getViewBinding(): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun showErrorMessage(error: String) {
        Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoader() {
        binding.progressBar.isVisible = true
    }

    override fun hideLoader() {
        binding.progressBar.isVisible = false
    }

    override fun validateInputs(isValid: Boolean) {
        binding.btnRegister.isEnabled = isValid
    }

    override fun navigateToHome() {
        (requireActivity() as? AuthActivity)?.navigateToHome()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        registerPresenter.detachView(this)
        registerPresenter.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
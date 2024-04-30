package com.reza.auth.ui.emailLink

import android.content.Context
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.reza.auth.databinding.FragmentEmailLinkBinding
import com.reza.auth.ui.AuthActivity
import com.reza.auth.ui.login.LoginContract
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val DEBOUNCING_TIME = 300L

class EmailLinkFragment :
    BaseFragment<FragmentEmailLinkBinding>(), EmailLinkContract.View {

    companion object {
        fun newInstance() = EmailLinkFragment()
    }


    private var emailLinkClickHandler: EmailLinkContract.EmailLinkClickHandler? = null

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var presenter: EmailLinkContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? AuthActivity)?.authComponent?.inject(this)
        emailLinkClickHandler = context as EmailLinkContract.EmailLinkClickHandler
    }

    override fun onDetach() {
        super.onDetach()
        emailLinkClickHandler = null
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

    override fun setupUi() {

    }

    override fun registerView() {
        presenter.attachView(this)
    }

    override fun setupListeners() {
        binding.apply {
            // buttons
            imgBack.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    emailLinkClickHandler?.onBackClicked()
                }.addTo(compositeDisposable)

            edtEmail.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    presenter.validateEmail(it.toString())
                }.addTo(compositeDisposable)

            btnRegister.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.loginUserWithEmailLink(email = edtEmail.text.toString().trim(),)
                }
                .addTo(compositeDisposable)
        }
    }

    override fun getViewBinding(): FragmentEmailLinkBinding {
        return FragmentEmailLinkBinding.inflate(layoutInflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView(this)
        presenter.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
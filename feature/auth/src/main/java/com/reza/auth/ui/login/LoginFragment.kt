package com.reza.auth.ui.login

import android.app.Activity
import android.content.Context
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.GoogleAuthProvider
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.reza.auth.databinding.FragmentLoginBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val DEBOUNCING_TIME = 300L

class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginContract.View {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private var loginFragmentClickHandler: LoginContract.LoginFragmentClickHandler? = null

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var presenter: LoginContract.Presenter

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    GoogleSignIn.getSignedInAccountFromIntent(result.data)
                        .result?.let {
                            val credentials = GoogleAuthProvider.getCredential(it.idToken, null)
                            presenter.loginWithCredentials(credentials)
                        }
                } catch (exp: ApiException) {
                    Timber.e(exp)
                }
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? AuthActivity)?.authComponent?.inject(this)
        loginFragmentClickHandler = context as LoginContract.LoginFragmentClickHandler
    }

    override fun setupUi() {

    }

    override fun registerView() {
        presenter.attachView(this)
    }

    override fun setupListeners() {
        binding.apply {
            imgEmail.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loginFragmentClickHandler?.onNavigateToRegister()
                }.addTo(compositeDisposable)

            imgGoogle.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    googleLauncher.launch(googleSignInClient.signInIntent)
                }
                .addTo(compositeDisposable)

            btnLogin.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    presenter.loginUserWithEmailAndPassword(
                        email = edtEmail.text.toString().trim(),
                        password = edtPassword.text.toString().trim()
                    )
                }.addTo(compositeDisposable)

            btnLoginWithEmailLink.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    loginFragmentClickHandler?.onEmailLinkClicked()
                }.addTo(compositeDisposable)

            edtEmail.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    presenter.validateEmail(it.toString())
                }.addTo(compositeDisposable)

            edtPassword.textChanges()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    presenter.validatePassword(it.toString())
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
        binding.progressBar.isVisible = true
    }

    override fun hideLoader() {
        binding.progressBar.isVisible = false
    }

    override fun validateInputs(isValid: Boolean) {
        binding.btnLogin.isEnabled = isValid
    }

    override fun navigateToHome() {
        (requireActivity() as? AuthActivity)?.navigateToHome()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView(this)
        presenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
        loginFragmentClickHandler = null
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
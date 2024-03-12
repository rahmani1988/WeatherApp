package com.reza.auth.ui.login

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.GoogleAuthProvider
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.reza.auth.BuildConfig
import com.reza.auth.databinding.FragmentLoginBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
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

    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    GoogleSignIn.getSignedInAccountFromIntent(result.data).result?.let {
                        val credentials = GoogleAuthProvider.getCredential(it.idToken, null)
                        loginPresenter.loginWithCredentials(credentials)
                    }
                } catch (exp: ApiException) {
                    Log.d("naghi", "ApiException. ${exp.message}")
                }
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? AuthActivity)?.authComponent?.inject(this)
    }

    override fun setupUi() {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_SING_IN_WEB_CLIENT_ID)
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(requireActivity(), options)

        googleSignInClient.signOut()
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

            imgGoogle.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribe {
                    googleLauncher.launch(googleSignInClient.signInIntent)
                }
                .addTo(compositeDisposable)

            btnLogin.clicks()
                .debounce(DEBOUNCING_TIME, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    loginPresenter.loginUserWithEmailAndPassword(
                        email = edtEmail.text.toString().trim(),
                        password = edtPassword.text.toString().trim()
                    )
                }.addTo(compositeDisposable)

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
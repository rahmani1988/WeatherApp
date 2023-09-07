package com.reza.auth.ui

import com.reza.core.R
import com.reza.auth.data.repository.AuthRepository
import com.reza.core.util.string.StringResolver
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
private const val PASSWORD_REGEX = "^" + // start-of-string
        "(?=.*[0-9])" + // a digit must occur at least once
        "(?=.*[a-z])" + // a lower case letter must occur at least once
        "(?=.*[A-Z])" + // an upper case letter must occur at least once
        "(?=.*[@#\$%^&+=!])" + // a special character must occur at least once you can replace with your special characters
        "(?=\\\\S+\$)" + // no whitespace allowed in the entire string
        ".{4,}" + // anything, at least six places though
        "\$" // end-of-string

class AuthPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val stringResolver: StringResolver
) : AuthContract.Presenter {

    private var view: AuthContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    private val isEmailValid = BehaviorProcessor.create<Boolean>()
    private val isPasswordValid = BehaviorProcessor.create<Boolean>()

    init {
        /**
         * combining two flowable to validate both inputs
         */
        compositeDisposable.add(
            Flowable.combineLatest(isPasswordValid, isEmailValid) { isPasswordValid, isEmailValid ->
                isEmailValid && isPasswordValid
            }.subscribe { isValid ->
                view?.validateInputs(isValid = isValid)
            }
        )
    }

    override fun createUserWithEmailAndPassword(email: String, password: String) {
        compositeDisposable.add(
            authRepository.registerUserWithEmailAndPassword(
                email = email,
                password = password
            ).subscribe({
                view?.navigateToHome()
            }, { error ->
                view?.showErrorMessage(error.message ?: stringResolver.getString(R.string.general_error))
            })
        )
    }

    override fun validateEmail(email: String) {
        if (email.matches(EMAIL_REGEX.toRegex())) {
            isEmailValid.onNext(true)
        } else {
            isEmailValid.onNext(false)
        }
    }

    override fun validatePassword(password: String) {
        if (password.matches(PASSWORD_REGEX.toRegex())) {
            isPasswordValid.onNext(true)
        } else {
            isPasswordValid.onNext(false)
        }
    }

    override fun attachView(view: AuthContract.View) {
        this.view = view
    }

    override fun detachView(view: AuthContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
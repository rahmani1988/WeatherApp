package com.reza.auth.ui

import com.reza.core.R
import com.reza.auth.data.repository.AuthRepository
import com.reza.core.util.string.StringResolver
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject



class AuthPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val stringResolver: StringResolver
) : AuthContract.Presenter {

    private var view: AuthContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    private val isEmailValid = BehaviorProcessor.createDefault(false)
    private val isPasswordValid = BehaviorProcessor.createDefault(false)

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

    }

    override fun validateEmail(email: String) {
        if (email.matches("".toRegex())) {
            isEmailValid.onNext(true)
        } else {
            isEmailValid.onNext(false)
        }
    }

    override fun validatePassword(password: String) {
        if (password.matches("".toRegex())) {
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
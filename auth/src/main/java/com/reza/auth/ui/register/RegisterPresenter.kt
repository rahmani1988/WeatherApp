package com.reza.auth.ui.register

import com.reza.auth.data.repository.AuthRepository
import com.reza.auth.ui.AuthContract
import com.reza.core.R
import com.reza.core.util.string.StringResolver
import com.reza.core.util.validation.DefaultValidator
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val stringResolver: StringResolver,
    private val validator: DefaultValidator,
    private val compositeDisposable: CompositeDisposable
) : RegisterContract.Presenter {

    private var view: RegisterContract.View? = null
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
        compositeDisposable.add(
            authRepository.registerUserWithEmailAndPassword(
                email = email,
                password = password
            ).subscribe({
                view?.navigateToHome()
            }, { error ->
                view?.showErrorMessage(
                    error.message ?: stringResolver.getString(R.string.general_error)
                )
            })
        )
    }

    override fun validateEmail(email: String) {
        isEmailValid.onNext(validator.isEmailValid(email))
    }

    override fun validatePassword(password: String) {
        isPasswordValid.onNext(validator.isPasswordValid(password))
    }

    override fun attachView(view: RegisterContract.View) {

    }

    override fun detachView(view: RegisterContract.View) {

    }

    override fun destroy() {

    }
}
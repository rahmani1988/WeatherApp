package com.reza.auth.ui.register

import com.reza.auth.data.repository.AuthRepository
import com.reza.core.R
import com.reza.core.di.IoSchedulers
import com.reza.core.di.MainSchedulers
import com.reza.core.util.string.StringResolver
import com.reza.core.util.validation.DefaultValidator
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.processors.BehaviorProcessor
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val stringResolver: StringResolver,
    private val validator: DefaultValidator,
    private val compositeDisposable: CompositeDisposable,
    @IoSchedulers private val ioScheduler: Scheduler,
    @MainSchedulers private val mainScheduler: Scheduler
) : RegisterContract.Presenter {

    private var view: RegisterContract.View? = null
    private val isEmailValid = BehaviorProcessor.createDefault(false)
    private val isPasswordValid = BehaviorProcessor.createDefault(false)

    init {
        /**
         * combining two flowable to validate both inputs
         */
        Flowable.combineLatest(isPasswordValid, isEmailValid) { isPasswordValid, isEmailValid ->
            isEmailValid && isPasswordValid
        }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe { isValid ->
                view?.validateInputs(isValid = isValid)
            }.addTo(compositeDisposable)
    }

    override fun createUserWithEmailAndPassword(email: String, password: String) {
        view?.showLoader()
        authRepository.registerUserWithEmailAndPassword(
            email = email,
            password = password
        ).subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onComplete = {
                    view?.hideLoader()
                    view?.navigateToHome()
                }, onError = { error ->
                    view?.hideLoader()
                    view?.showErrorMessage(
                        error.message ?: stringResolver.getString(R.string.general_error)
                    )
                }
            ).addTo(compositeDisposable)
    }

    override fun validateEmail(email: String) {
        isEmailValid.onNext(validator.isEmailValid(email))
    }

    override fun validatePassword(password: String) {
        isPasswordValid.onNext(validator.isPasswordValid(password))
    }

    override fun attachView(view: RegisterContract.View) {
        this.view = view
    }

    override fun detachView(view: RegisterContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
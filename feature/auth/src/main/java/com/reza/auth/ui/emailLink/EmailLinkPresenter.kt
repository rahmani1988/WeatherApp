package com.reza.auth.ui.emailLink

import com.google.firebase.auth.ktx.actionCodeSettings
import com.reza.auth.data.repository.AuthRepository
import com.reza.core.di.IoSchedulers
import com.reza.core.di.MainSchedulers
import com.reza.core.util.string.StringResolver
import com.reza.core.util.validation.DefaultValidator
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.processors.BehaviorProcessor
import javax.inject.Inject

class EmailLinkPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val stringResolver: StringResolver,
    private val compositeDisposable: CompositeDisposable,
    @IoSchedulers private val ioScheduler: Scheduler,
    @MainSchedulers private val mainScheduler: Scheduler,
    private val validator: DefaultValidator,
) : EmailLinkContract.Presenter {

    private var view: EmailLinkContract.View? = null

    private val isEmailValid = BehaviorProcessor.createDefault(false)
    override fun loginUserWithEmailLink(email: String) {
        val actionCodeSettings = actionCodeSettings {
            // URL you want to redirect back to. The domain (www.example.com) for this
            // URL must be whitelisted in the Firebase Console.
            url = "https://www.example.com/finishSignUp?cartId=1234"
            // This must be true
            handleCodeInApp = true
            setIOSBundleId("com.example.ios")
            setAndroidPackageName(
                "com.reza.weatherapp",
                true, // installIfNotAvailable
                "12", // minimumVersion
            )
        }
    }

    override fun validateEmail(email: String) {
        isEmailValid.onNext(validator.isEmailValid(email))
    }

    override fun attachView(view: EmailLinkContract.View) {
        this.view = view
    }

    override fun detachView(view: EmailLinkContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
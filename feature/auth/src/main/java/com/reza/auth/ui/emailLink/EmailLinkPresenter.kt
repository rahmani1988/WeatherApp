package com.reza.auth.ui.emailLink

import com.reza.auth.data.repository.AuthRepository
import com.reza.core.di.IoSchedulers
import com.reza.core.di.MainSchedulers
import com.reza.core.util.string.StringResolver
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class EmailLinkPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val stringResolver: StringResolver,
    private val compositeDisposable: CompositeDisposable,
    @IoSchedulers private val ioScheduler: Scheduler,
    @MainSchedulers private val mainScheduler: Scheduler
) : EmailLinkContract.Presenter {

    private var view: EmailLinkContract.View? = null
    override fun loginUserWithEmailLink(email: String) {

    }

    override fun validateEmail(email: String) {

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
package com.reza.start.ui.start

import com.reza.core.util.user.UserManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StartPresenter @Inject constructor(
    private val userManager: UserManager
) : StartContract.Presenter {

    private var view: StartContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun getUser() {
        if (userManager.isUserSignedIn()) {
            view?.navigateToDashboard()
        } else {
            view?.navigateToAuth()
        }
    }

    override fun attachView(view: StartContract.View) {
        this.view = view
    }

    override fun detachView(view: StartContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
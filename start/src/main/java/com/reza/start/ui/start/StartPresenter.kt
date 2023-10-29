package com.reza.start.ui.start

import com.reza.core.models.local.analytics.Analytics
import com.reza.core.util.analytics.AnalyticsHelper
import com.reza.core.util.user.UserManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StartPresenter @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val userManager: UserManager
) : StartContract.Presenter {

    private var view: StartContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun getUser() {
        if (userManager.isUserSignedIn()) view?.navigateToHome() else view?.navigateToAuth()
    }

    override fun sendAnalyticsEvent() {
        val params = arrayOf(
            Analytics.ParamData(Analytics.Param.SCREEN_NAME, StartActivity::class.java.name),
            Analytics.ParamData(
                Analytics.Param.SCREEN_CLASS,
                StartActivity::class.java.simpleName
            ),
        )
        analyticsHelper.logEvent(
            event = Analytics.Event.SCREEN_VIEW,
            params = params
        )
    }

    override fun attachView(view: StartContract.View) {
        this.view = view
    }

    override fun detachView(view: StartContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear() // clear all, but can accept new disposable
        compositeDisposable.dispose() // clear all and set isDisposed = true, so it will not accept any new disposable
    }
}
package com.reza.start.ui.start

import com.reza.core.di.IoSchedulers
import com.reza.core.di.MainSchedulers
import com.reza.core.models.local.analytics.Analytics
import com.reza.core.util.analytics.AnalyticsHelper
import com.reza.core.util.user.UserManager
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class StartPresenter @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val userManager: UserManager,
    @IoSchedulers private val ioScheduler: Scheduler,
    @MainSchedulers private val mainScheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable,
) : StartContract.Presenter {

    private var view: StartContract.View? = null

    override fun getUser() {
        userManager.isUserLoggedIn()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onSuccess = {
                    view?.navigateToHome()
                },
                onError = {
                    view?.navigateToAuth()
                }
            )
            .addTo(compositeDisposable)
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
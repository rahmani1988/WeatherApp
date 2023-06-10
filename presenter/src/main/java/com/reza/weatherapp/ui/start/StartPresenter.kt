package com.reza.weatherapp.ui.start

import com.reza.weatherapp.di.IoSchedulers
import com.reza.weatherapp.di.MainSchedulers
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class StartPresenter @Inject constructor(
    @IoSchedulers private val ioScheduler: Scheduler,
    @MainSchedulers private val mainScheduler: Scheduler
) : StartContract.Presenter {

    private var view: StartContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    override fun getUserStatus() {

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
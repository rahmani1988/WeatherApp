package com.reza.home.ui

import android.util.Log
import com.reza.core.util.user.DefaultUserManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(val userManager: DefaultUserManager) : HomeContract.Presenter {

    private var view: HomeContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    override fun attachView(view: HomeContract.View) {
        userManager.getUserInfo()
        this.view = view
    }

    override fun detachView(view: HomeContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}
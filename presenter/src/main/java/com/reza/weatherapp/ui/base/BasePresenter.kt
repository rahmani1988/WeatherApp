package com.reza.weatherapp.ui.base

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun destroy()
}
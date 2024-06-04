package com.reza.home.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.reza.core.ui.base.BaseActivity
import com.reza.home.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding>(), HomeContract.View {

    private lateinit var homeComponent: HomeComponent

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        // To initialise startComponent
        homeComponent = (applicationContext as HomeComponentProvider).provideHomeComponent()
        homeComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun registerView() {
        presenter.attachView(this)
    }

    override fun setupUi() {

    }

    override fun setupSubscribers() {

    }

    override fun setupListeners() {

    }

    override fun getViewBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
        presenter.destroy()
    }
}
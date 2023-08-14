package com.reza.start.ui.start

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.analytics.FirebaseAnalytics
import com.reza.core.models.local.analytics.Analytics
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.analytics.AnalyticsHelper
import com.reza.start.databinding.ActivityStartBinding
import javax.inject.Inject

class StartActivity : BaseActivity<ActivityStartBinding>(), StartContract.View {

    private lateinit var startComponent: StartComponent

    @Inject
    lateinit var presenter: StartContract.Presenter

    @Inject
    lateinit var analytics: AnalyticsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        // To initialise startComponent
        startComponent = (applicationContext as StartComponentProvider).provideStartComponent()
        startComponent.inject(this)

        super.onCreate(savedInstanceState)

        // Enable support for Splash Screen API for proper Android 12+ support
        installSplashScreen()
    }

    override fun registerView() {
        presenter.attachView(this)
    }

    override fun setupUi() {
        sendAnalyticsData()
    }

    private fun sendAnalyticsData() {
        val params = arrayOf(
            Analytics.ParamData(Analytics.Param.SCREEN_NAME, StartActivity::class.java.name),
            Analytics.ParamData(Analytics.Param.SCREEN_CLASS, StartActivity::class.java.simpleName),
        )
        analytics.logEvent(
            event = Analytics.Event.SCREEN_VIEW,
            params = *params
        )
    }

    override fun setupSubscribers() {

    }

    override fun setupListeners() {

    }

    override fun getViewBinding(): ActivityStartBinding {
        return ActivityStartBinding.inflate(layoutInflater)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun navigateToAuth() {

    }

    override fun navigateToDashboard() {

    }

    override fun showError(message: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
        presenter.destroy()
    }
}
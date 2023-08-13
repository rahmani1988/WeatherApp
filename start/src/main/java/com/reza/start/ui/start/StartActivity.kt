package com.reza.start.ui.start

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.analytics.FirebaseAnalytics
import com.reza.core.models.local.analytics.Event
import com.reza.core.models.local.analytics.FirebaseParamDate
import com.reza.core.models.local.analytics.Param
import com.reza.core.models.local.analytics.ParamData
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
        // sending test event
        binding.btnStart.setOnClickListener {
            analytics.logCustomEvent(Event.TEST, ParamData(Param.PARAM_TEST, "reza"))
            val params = arrayOf(
                FirebaseParamDate(FirebaseAnalytics.Param.LEVEL, "1"),
                FirebaseParamDate(FirebaseAnalytics.Param.CAMPAIGN, "2")
            )
            analytics.logEvent(
                event = FirebaseAnalytics.Event.LEVEL_START,
                params = params
            )
        }
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
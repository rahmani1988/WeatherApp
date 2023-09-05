package com.reza.start.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.analytics.FirebaseAnalytics
import com.reza.core.models.local.analytics.Analytics
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.analytics.AnalyticsHelper
import com.reza.core.util.constant.AUTH_ACTIVITY
import com.reza.core.util.constant.HOME_ACTIVITY
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
            Analytics.ParamData(
                Analytics.Param.SCREEN_CLASS,
                StartActivity::class.java.simpleName
            ),
        )
        analytics.logEvent(
            event = Analytics.Event.SCREEN_VIEW,
            params = params
        )
    }

    override fun setupSubscribers() {
        /* NO-OP */
    }

    override fun setupListeners() {
        binding.apply {
            btnStart.setOnClickListener {
                presenter.getUser()
            }
        }
    }

    override fun getViewBinding(): ActivityStartBinding {
        return ActivityStartBinding.inflate(layoutInflater)
    }

    override fun navigateToAuth() {
        val intent = Intent().apply {
            setClassName(this@StartActivity, AUTH_ACTIVITY)
        }
        startActivity(intent)
        finish()
    }

    override fun navigateToDashboard() {
        val intent = Intent().apply {
            setClassName(this@StartActivity, HOME_ACTIVITY)
        }
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
        presenter.destroy()
    }
}
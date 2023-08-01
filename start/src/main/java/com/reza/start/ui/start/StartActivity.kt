package com.reza.start.ui.start

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.reza.core.ui.base.BaseActivity
import com.reza.start.databinding.ActivityStartBinding
import javax.inject.Inject

class StartActivity : BaseActivity(), StartContract.View {

    private lateinit var binding: ActivityStartBinding
    private lateinit var startComponent: StartComponent

    @Inject
    lateinit var presenter: StartContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        // To initialise startComponent
        startComponent = (applicationContext as StartComponentProvider).provideStartComponent()
        startComponent.inject(this)

        super.onCreate(savedInstanceState)

        // Enable support for Splash Screen API for proper Android 12+ support
        installSplashScreen()
        // To initialise Binding
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
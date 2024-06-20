package com.reza.start.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.constant.Constant
import com.reza.start.databinding.ActivityStartBinding
import timber.log.Timber
import javax.inject.Inject

class StartActivity : BaseActivity<ActivityStartBinding>(), StartContract.View {

    private lateinit var startComponent: StartComponent

    @Inject
    lateinit var presenter: StartContract.Presenter

    override fun getViewBinding(): ActivityStartBinding {
        return ActivityStartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // To initialise startComponent
        startComponent = (applicationContext as StartComponentProvider).provideStartComponent()
        startComponent.inject(this)

        super.onCreate(savedInstanceState)

        // Enable support for Splash Screen API for proper Android 12+ support
        installSplashScreen()

        getDynamicLink()
    }

    /**
     * Retrieves a dynamic link from Firebase and handles the navigation to the target activity.
     * It also extracts the deep link from the result and allows for further processing of the link data.
     */
    private fun getDynamicLink() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                // Navigate to the target activity based on the dynamic link path.
                handleDynamicLinkNavigation(pendingDynamicLinkData?.link?.path)

                // Extract the deep link from the result.
                val deepLink: Uri? = pendingDynamicLinkData?.link

                // Handle the deep link (e.g., open content, apply promotional credit).
                // ...
            }
            .addOnFailureListener(this) { e ->
                // Log the error using Timber.
                Timber.e(e)
            }
    }

    /**
     * Handles navigation based on the path extracted from a dynamic link.
     *
     * @param path The path segment of the dynamic link, which determines the navigation target.
     */
    private fun handleDynamicLinkNavigation(path: String?) {
        when (path) {
            "/home" -> {
                navigateToHome()
            }
            // Add more cases for other possible paths and their corresponding navigation actions.
            else -> Unit // Handle cases where the path is null or doesn't match any defined routes.
        }
    }

    override fun registerView() {
        presenter.attachView(this)
    }

    override fun setupUi() {
        presenter.sendAnalyticsEvent()
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

    override fun navigateToAuth() {
        val intent = Intent().apply {
            setClassName(this@StartActivity, Constant.Activities.AUTH_ACTIVITY.path)
        }
        startActivity(intent)
        finish()
    }

    override fun navigateToHome() {
        val intent = Intent().apply {
            setClassName(this@StartActivity, Constant.Activities.HOME_ACTIVITY.path)
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
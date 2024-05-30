package com.reza.start.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.ktx.Firebase
import com.reza.core.ui.base.BaseActivity
import com.reza.core.util.constant.Constant
import com.reza.start.databinding.ActivityStartBinding
import javax.inject.Inject


class StartActivity : BaseActivity<ActivityStartBinding>(), StartContract.View {
    // TODO: user's current location has to be taken in this activity

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

    private fun getDynamicLink() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData: PendingDynamicLinkData? ->
                Log.i("naghi", "getDynamicLink: ${pendingDynamicLinkData?.link}")
                if(pendingDynamicLinkData?.link?.path?.contains("/home") == true) {
                   //navigateToHome()
                }
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link

                }

                // Handle the deep link. For example, open the linked
                // content, or apply promotional credit to the user's
                // account.
                // ...
            }
            .addOnFailureListener(this) { e -> Log.w("naghi", "getDynamicLink:onFailure", e) }
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
package com.reza.start.ui.start

import com.reza.core.models.local.analytics.Analytics
import com.reza.core.util.analytics.AnalyticsHelper
import com.reza.core.util.user.UserManager
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class StartPresenterTest {

    @Mock
    private lateinit var analyticsHelper: AnalyticsHelper

    @Mock
    private lateinit var userManager: UserManager

    @Mock
    private lateinit var view: StartContract.View

    private lateinit var presenter: StartPresenter

    private val testScheduler = Schedulers.trampoline()
    private val compositeDisposable = CompositeDisposable()

    @Before
    fun setup() {
        presenter = StartPresenter(
            analyticsHelper = analyticsHelper,
            userManager = userManager,
            ioScheduler = testScheduler,
            mainScheduler = testScheduler,
            compositeDisposable = compositeDisposable
        )
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        presenter.detachView(view)
    }

    @Test
    fun `should navigate to home`() {
        // Given
        whenever(userManager.isUserLoggedIn()).thenReturn(Single.just(true))

        // When
        presenter.getUser()

        // Then
        verify(view).navigateToHome()
        verify(view, times(1)).navigateToHome()
    }

    @Test
    fun `should navigate to auth`() {
        // Given
        whenever(userManager.isUserLoggedIn()).thenReturn(Single.error(Exception("")))

        // When
        presenter.getUser()

        // Then
        verify(view).navigateToAuth()
        verify(view, times(1)).navigateToAuth()
    }

    @Test
    fun `should send analytics event`() {
        // Given
        val event = Analytics.Event.SCREEN_VIEW
        val params = arrayOf(
            Analytics.ParamData(Analytics.Param.SCREEN_NAME, StartActivity::class.java.name),
            Analytics.ParamData(
                Analytics.Param.SCREEN_CLASS,
                StartActivity::class.java.simpleName
            )
        )

        // When
        presenter.sendAnalyticsEvent()

        // Then
        verify(analyticsHelper).logEvent(event = event, params = params)
        verify(analyticsHelper, times(1)).logEvent(
            event = Analytics.Event.SCREEN_VIEW,
            params = params
        )
    }
}
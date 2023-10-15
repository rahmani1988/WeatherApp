package com.reza.core.util.string

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

// TODO: Robolectric should be used here to simulate context

@RunWith(RobolectricTestRunner::class)
class DefaultStringResolverTest {

    private lateinit var stringResolver: DefaultStringResolver

    @Before
    fun setup() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        stringResolver = DefaultStringResolver(appContext)
    }

    @Test
    fun `test`() {
        // Given


        // When
     //   val result = stringResolver.getString(R.string)

        // Then
    }

}
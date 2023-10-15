package com.reza.core.util.user

import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.whenever

class DefaultUserManagerTest {

    /**
     * This rule can be used with mockito to init all mock properties
     */
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var userManager: DefaultUserManager

    @Before
    fun setup() {
        userManager = DefaultUserManager(firebaseAuth)
    }

    @Test
    fun `should return false if user has not signed in`() {
        // Given
        whenever(firebaseAuth.currentUser).thenReturn(null)

        // When
        val isUserSignedIn = userManager.isUserSignedIn()

        // Then
        assertThat(isUserSignedIn).isFalse()
    }

    @Test
    fun `should return true if user has signed in`() {
        // Given
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)

        // When
        val isUserSignedIn = userManager.isUserSignedIn()

        // Then
        assertThat(isUserSignedIn).isTrue()
    }
}
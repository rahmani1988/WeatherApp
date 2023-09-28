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
    fun `user has not signed in`() {
        // Given
        whenever(firebaseAuth.currentUser).thenReturn(null)

        // When
        val result = userManager.isUserSignedIn()

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `user has signed in`() {
        // Given
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)

        // When
        val result = userManager.isUserSignedIn()

        // Then
        assertThat(result).isTrue()
    }
}
package com.reza.core.util.user

import com.google.firebase.auth.FirebaseAuth
import com.reza.core.models.local.user.User
import javax.inject.Inject

class DefaultUserManager @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserManager {

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getUserInfo(): User? {
        return firebaseAuth.currentUser?.let {
            User(
                email = it.email,
                displayName = it.displayName,
                photoUrl = it.photoUrl,
                isEmailVerified = it.isEmailVerified,
                userId = it.uid
            )
        }
    }
}
package com.reza.core.util.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class DefaultUserManager @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserManager {

    override fun isUserSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
package com.reza.core.util.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class DefaultUserManager @Inject constructor() : UserManager {

    private var auth: FirebaseAuth = Firebase.auth

    override fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }
}
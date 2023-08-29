package com.reza.core.util.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DefaultUserHelper: UserHelper {

    private var auth: FirebaseAuth = Firebase.auth

    override fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun signUp(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//
//
//            }
    }
}
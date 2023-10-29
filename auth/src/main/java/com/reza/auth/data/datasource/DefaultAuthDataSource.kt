package com.reza.auth.data.datasource

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DefaultAuthDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthDataSource {
    override fun registerUserWithEmailAndPassword(email: String, password: String): Completable {
        return Completable.create { emitter ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(task.exception ?: Exception("Failed to register"))
                    }
                }
        }
    }
}
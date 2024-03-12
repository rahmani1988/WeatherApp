package com.reza.auth.data.datasource.remote

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.reza.auth.data.datasource.remote.AuthRemoteDataSource
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DefaultAuthRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRemoteDataSource {
    override fun registerUserWithEmailAndPassword(email: String, password: String): Completable {
        return Completable.create { emitter ->
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to register"))
                        }
                    }
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    override fun loginUserWithEmailAndPassword(email: String, password: String): Completable {
        return Completable.create { emitter ->
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to login"))
                        }
                    }
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    override fun loginWithCredential(authCredential: AuthCredential): Completable {
        return Completable.create { emitter ->
            try {
                firebaseAuth.signInWithCredential(authCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to login"))
                        }
                    }
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }
}
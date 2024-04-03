package com.reza.core.util.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.reza.core.models.local.user.User
import io.reactivex.rxjava3.core.Completable
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
                userId = it.uid,
                providers = it.providerData.map { info -> info.providerId }
            )
        }
    }

    override fun updatePassword(newPassword: String): Completable {
        return Completable.create { emitter ->
            try {
                firebaseAuth.currentUser?.let { user ->
                    user.updatePassword(newPassword).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            emitter.onError(
                                task.exception ?: Exception("failed to change password")
                            )
                        }
                    }
                } ?: emitter.onError(Exception("no user"))
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    // TODO: do R&D to find a way to update photo as well
    override fun updateProfile(name: String): Completable {
        return Completable.create { emitter ->
            try {
                firebaseAuth.currentUser?.let { user ->
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }
                    user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to update profile"))
                        }
                    }
                } ?: emitter.onError(Exception("no user"))
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    override fun delete(): Completable {
        return Completable.create { emitter ->
            try {
                firebaseAuth.currentUser?.let { user ->
                    user.delete().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onComplete()
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to delete user"))
                        }
                    }
                } ?: emitter.onError(Exception("no user"))
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }
}



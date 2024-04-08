package com.reza.core.util.user

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.reza.core.models.local.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DefaultUserManager @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : UserManager {

    override fun isUserLoggedIn(): Single<Boolean> {
        return Single.create { emitter ->
            firebaseAuth.currentUser?.let {
                emitter.onSuccess(true)
            } ?: emitter.onError(Exception("User is not logged in"))
        }
    }

    override fun getUserInfo(): Single<User> {
        return Single.create { emitter ->
            firebaseAuth.currentUser?.let {
                val user = User(email = it.email,
                    displayName = it.displayName,
                    photoUrl = it.photoUrl,
                    isEmailVerified = it.isEmailVerified,
                    userId = it.uid,
                    providers = it.providerData.map { info -> info.providerId })
                emitter.onSuccess(user)
            } ?: emitter.onError(Exception("No user found"))
        }
    }

    override fun updatePassword(newPassword: String): Single<Boolean> {
        return Single.create { emitter ->
            try {
                firebaseAuth.currentUser?.let { user ->
                    user.updatePassword(newPassword).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onSuccess(true)
                        } else {
                            emitter.onError(
                                task.exception ?: Exception("failed to change password")
                            )
                        }
                    }
                } ?: emitter.onError(Exception("no user"))
            } catch (exp: FirebaseAuthRecentLoginRequiredException) {
                emitter.onError(exp)
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    // TODO: do R&D to find a way to update photo as well
    override fun updateProfile(name: String): Single<Boolean> {
        return Single.create { emitter ->
            try {
                firebaseAuth.currentUser?.let { user ->
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }
                    user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onSuccess(true)
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to update profile"))
                        }
                    }
                } ?: emitter.onError(Exception("no user"))
            } catch (exp: FirebaseAuthRecentLoginRequiredException) {
                emitter.onError(exp)
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    override fun delete(): Single<Boolean> {
        return Single.create { emitter ->
            try {
                firebaseAuth.currentUser?.let { user ->
                    user.delete().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onSuccess(true)
                        } else {
                            emitter.onError(task.exception ?: Exception("failed to delete user"))
                        }
                    }
                } ?: emitter.onError(Exception("no user"))
            } catch (exp: FirebaseAuthRecentLoginRequiredException) {
                emitter.onError(exp)
            } catch (exp: Exception) {
                emitter.onError(exp)
            }
        }
    }

    override fun reAuthenticateUser(
        email: String?,
        password: String?,
        authCredential: AuthCredential?
    ): Completable {
        return Completable.create { emitter ->
            firebaseAuth.currentUser?.let { user ->
                if (authCredential != null) {
                    // Google sing in method has been used
                    user.reauthenticate(authCredential).addOnCompleteListener {
                        emitter.onComplete()
                    }
                } else {
                    // Email and password sing in method has been used
                    if (email == null || password == null) {
                        emitter.onError(Exception("Email and password have to be not null"))
                    } else {
                        val credential =
                            EmailAuthProvider.getCredential(email, password)
                        user.reauthenticate(credential).addOnCompleteListener {
                            emitter.onComplete()
                        }
                    }
                }
            } ?: emitter.onError(Exception("No user found"))
        }
    }
}
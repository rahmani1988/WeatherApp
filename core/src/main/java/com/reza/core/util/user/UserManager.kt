package com.reza.core.util.user

import com.google.firebase.auth.AuthCredential
import com.reza.core.models.local.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserManager {
    fun isUserLoggedIn(): Single<Boolean>
    fun getUserInfo(): Single<User>
    fun updatePassword(newPassword: String): Single<Boolean>
    fun updateProfile(name: String): Single<Boolean>
    fun delete(): Single<Boolean>
    fun reAuthenticateUser(
        email: String?,
        password: String?,
        authCredential: AuthCredential?
    ): Completable
    fun signOut(): Completable
}
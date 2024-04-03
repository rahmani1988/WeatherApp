package com.reza.core.util.user

import com.reza.core.models.local.user.User
import io.reactivex.rxjava3.core.Completable

interface UserManager {
    fun isUserLoggedIn(): Boolean
    fun getUserInfo(): User?
    fun updatePassword(newPassword: String): Completable
    fun updateProfile(name: String): Completable
    fun delete(): Completable
}
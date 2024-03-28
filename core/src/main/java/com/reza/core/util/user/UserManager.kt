package com.reza.core.util.user

import com.reza.core.models.local.user.User

interface UserManager {
    fun isUserLoggedIn(): Boolean
    fun getUserInfo(): User?
}
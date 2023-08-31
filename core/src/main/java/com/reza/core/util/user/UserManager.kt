package com.reza.core.util.user

interface UserManager {
    fun isUserSignedIn(): Boolean
    fun signUp(email: String, password: String)
}
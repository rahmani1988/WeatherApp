package com.reza.core.util.user

interface UserHelper {
    fun isUserSignedIn(): Boolean
    fun signUp(email: String, password: String)
}
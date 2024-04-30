package com.reza.core.util.validation

interface Validator {
    fun isEmailValid(email: String) : Boolean
    fun isPasswordValid(password: String) : Boolean
}
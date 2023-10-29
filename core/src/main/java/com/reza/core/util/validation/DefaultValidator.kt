package com.reza.core.util.validation

import javax.inject.Inject

private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
private const val PASSWORD_REGEX = "^" + // start-of-string
        //"(?=.*[0-9])" + // a digit must occur at least once
        //"(?=.*[a-z])" + // a lower case letter must occur at least once
        //"(?=.*[A-Z])" + // an upper case letter must occur at least once
        //"(?=.*[@#\$%^&+=!])" + // a special character must occur at least once you can replace with your special characters
        //"(?=\\\\S+\$)" + // no whitespace allowed in the entire string
        ".{6,}" + // anything, at least six places though
        "\$" // end-of-string

class DefaultValidator @Inject constructor() : Validator {
    override fun isEmailValid(email: String): Boolean {
        return (email.matches(EMAIL_REGEX.toRegex()))
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.matches(PASSWORD_REGEX.toRegex())
    }
}
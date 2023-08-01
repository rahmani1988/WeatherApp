package com.reza.auth.ui

import com.reza.auth.ui.AuthComponent

interface AuthComponentProvider {

    fun provideAuthComponent(): AuthComponent
}
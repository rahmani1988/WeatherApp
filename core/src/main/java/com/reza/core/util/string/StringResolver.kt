package com.reza.core.util.string

import androidx.annotation.StringRes

interface StringResolver {
    fun getString(@StringRes resId: Int): String
}
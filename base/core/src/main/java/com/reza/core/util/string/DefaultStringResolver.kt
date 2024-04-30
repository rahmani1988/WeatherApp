package com.reza.core.util.string

import android.content.Context
import javax.inject.Inject

class DefaultStringResolver @Inject constructor(private val context: Context): StringResolver {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
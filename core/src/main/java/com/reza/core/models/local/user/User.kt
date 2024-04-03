package com.reza.core.models.local.user

import android.net.Uri

data class User(
    val email: String?,
    val displayName: String?,
    val photoUrl: Uri?,
    val isEmailVerified: Boolean?,
    val userId: String?,
    val providers: List<String>?
)

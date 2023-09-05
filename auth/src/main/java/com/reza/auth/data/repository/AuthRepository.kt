package com.reza.auth.data.repository

import io.reactivex.Completable

interface AuthRepository {
    fun registerUserWithEmailAndPassword(email: String, password: String): Completable
}
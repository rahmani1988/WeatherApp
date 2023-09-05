package com.reza.auth.data.datasource

import io.reactivex.Completable

interface AuthDataSource {
    fun registerUserWithEmailAndPassword(email: String, password: String): Completable
}
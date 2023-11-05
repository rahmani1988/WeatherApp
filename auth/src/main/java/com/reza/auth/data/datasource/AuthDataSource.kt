package com.reza.auth.data.datasource

import io.reactivex.rxjava3.core.Completable


interface AuthDataSource {
    fun registerUserWithEmailAndPassword(email: String, password: String): Completable

    fun loginUserWithEmailAndPassword(email: String, password: String): Completable
}
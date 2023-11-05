package com.reza.auth.data.repository

import io.reactivex.rxjava3.core.Completable


interface AuthRepository {
    fun registerUserWithEmailAndPassword(email: String, password: String): Completable
    fun loginUserWithEmailAndPassword(email: String, password: String): Completable
}
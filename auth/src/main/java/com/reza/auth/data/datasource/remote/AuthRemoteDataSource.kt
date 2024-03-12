package com.reza.auth.data.datasource.remote

import com.google.firebase.auth.AuthCredential
import io.reactivex.rxjava3.core.Completable


interface AuthRemoteDataSource {
    fun registerUserWithEmailAndPassword(email: String, password: String): Completable

    fun loginUserWithEmailAndPassword(email: String, password: String): Completable

    fun loginWithCredential(authCredential: AuthCredential): Completable
}
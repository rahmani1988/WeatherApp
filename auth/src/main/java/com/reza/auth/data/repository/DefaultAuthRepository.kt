package com.reza.auth.data.repository

import com.google.firebase.auth.AuthCredential
import com.reza.auth.data.datasource.remote.AuthRemoteDataSource
import io.reactivex.rxjava3.core.Completable

import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource) :
    AuthRepository {
    override fun registerUserWithEmailAndPassword(email: String, password: String): Completable {
        return authRemoteDataSource.registerUserWithEmailAndPassword(email = email, password = password)
    }

    override fun loginUserWithEmailAndPassword(email: String, password: String): Completable {
        return authRemoteDataSource.loginUserWithEmailAndPassword(email = email, password = password)
    }

    override fun loginWithCredential(authCredential: AuthCredential): Completable {
        return authRemoteDataSource.loginWithCredential(authCredential = authCredential)
    }
}
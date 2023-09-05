package com.reza.auth.data.repository

import com.reza.auth.data.datasource.AuthDataSource
import io.reactivex.Completable
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(private val authDataSource: AuthDataSource) :
    AuthRepository {
    override fun registerUserWithEmailAndPassword(email: String, password: String): Completable {
        return authDataSource.registerUserWithEmailAndPassword(email = email, password = password)
    }
}
package com.baben.apps.appformation3.domain.repositories
import com.baben.apps.appformation3.core.networking.NetworkErrorException
import com.baben.apps.appformation3.domain.models.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call


interface LoginRepository {

    @Throws(NetworkErrorException::class)
    suspend fun login(model: Login?): LoginResult?

    enum class LoginResult {
        SUCCESS,
        AUTH_ERROR,
        GENERAL_ERROR
    }

}
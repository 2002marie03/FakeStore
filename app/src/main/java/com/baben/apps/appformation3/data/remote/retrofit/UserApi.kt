package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.repositories.LoginRepository
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    @FormUrlEncoded
    @POST("https://fakestoreapi.com/auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String)
    :Call<LoginRepository.LoginResult>


    //TODO :: add Login and SingUp services here

    // login
    // singUp
}
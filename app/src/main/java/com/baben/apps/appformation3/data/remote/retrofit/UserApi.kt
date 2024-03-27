package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.models.User
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    //TODO :: add Login and SingUp services here



    @POST("https://fakestoreapi.com/auth/login")
     fun login(@Body login: Login?): String

    // login

    // singUp
}
package com.baben.apps.appformation3.data.remote.repositories

import android.content.SharedPreferences
import com.baben.apps.appformation3.data.remote.retrofit.UserApi
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository

class ApiLoginRepository(private val userApi: UserApi)  : LoginRepository {


    override  fun login(model: Login?): LoginRepository.LoginResult? {

        return try {
            val response = userApi.login(model)
            if (response!=null) {
                LoginRepository.LoginResult.SUCCESS
            } else {
                LoginRepository.LoginResult.AUTH_ERROR
            }
        } catch (e: Exception) {
            LoginRepository.LoginResult.GENERAL_ERROR
        }
    }



}
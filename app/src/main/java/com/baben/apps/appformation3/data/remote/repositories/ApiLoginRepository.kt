package com.baben.apps.appformation3.data.remote.repositories

import android.accounts.NetworkErrorException
import com.baben.apps.appformation3.data.remote.retrofit.UserApi
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject


class ApiLoginRepository(private val userApi: UserApi) : LoginRepository {

    override suspend fun login(model: Login?): LoginRepository.LoginResult {
        //TODO("Not yet implemented")
        if (model == null) return LoginRepository.LoginResult.GENERAL_ERROR

        return try {
            val response = userApi.login(model.username,model.password)
            if (response != null ) {

                LoginRepository.LoginResult.SUCCESS // Handle successful login
            } else {
                LoginRepository.LoginResult.AUTH_ERROR // Handle authentication errors
            }
        } catch (e: IOException) {
            e.printStackTrace()
            LoginRepository.LoginResult.GENERAL_ERROR
        }
    }


        /*
           val client = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add("username", model?.username ?: "")
                .add("password", model?.password ?: "")
                .build()

            val request = Request.Builder()
                .url("https://fakestoreapi.com/auth/login")
                .post(requestBody)
                .build()

            try {
                val response = client.newCall(request).execute()
                val responseBody = response.body()?.string()

                if (response.isSuccessful && responseBody != null) {
                    // Connexion réussie
                    return LoginRepository.LoginResult.SUCCESS
                } else {
                    // Erreur d'authentification ou erreur générale
                    return LoginRepository.LoginResult.AUTH_ERROR
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Erreur de connexion ou de lecture des données
                return LoginRepository.LoginResult.GENERAL_ERROR
            }
   */




}
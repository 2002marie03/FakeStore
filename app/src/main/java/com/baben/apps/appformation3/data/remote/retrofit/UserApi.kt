package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.models.User
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


interface UserApi {
    //TODO :: add Login and SingUp services here
    // login
    @FormUrlEncoded
    @POST("https://fakestoreapi.com/auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String):String

    // singUp
}


/*@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"
    @Provides
    @Singleton
    fun provideApi(): UserApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}*/

package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity

class AuthLocalStorage(private val context: Context) {

    companion object {
        val PREF:String="MY_PREF"
    }
    private val  sharedPreferences: SharedPreferences =  context.getSharedPreferences(  PREF
        , Context.MODE_PRIVATE)


    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getSavedToken(): String {
       return "hello"
    }


}
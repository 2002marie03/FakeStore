package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences


class AuthLocalStorage(private val context: Context) {

    companion object {
        val PREF:String="MY_PREF"
        val key:String="token"
    }
    private val  sharedPreferences: SharedPreferences =  context.getSharedPreferences(  PREF
        , Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, token)
        editor.apply()
    }

    fun getSavedToken(): String? {
       return sharedPreferences.getString(key,null)
    }

    fun userisconnected(): Boolean {
        val token = sharedPreferences.getString(key, null)
        return token != null
    }


}
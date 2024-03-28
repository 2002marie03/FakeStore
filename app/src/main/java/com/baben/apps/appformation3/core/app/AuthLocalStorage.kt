package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences

class AuthLocalStorage(private val context: Context) {
    companion object{
        val Shared_Pref_Label : String = "user_connexion_prefs"
    }
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Shared_Pref_Label, Context.MODE_PRIVATE)
    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
    fun getSavedToken(): String? {
        return sharedPreferences.getString("token",null)
    }

    fun isLoggedIn(): Boolean {
        val tokenstored = sharedPreferences.getString("token", null)
        return tokenstored != null}


}
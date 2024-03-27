package com.baben.apps.appformation3.presentation.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(
) {
    val username:MutableLiveData<kotlin.String> = MutableLiveData<kotlin.String>()
    private val password = MutableLiveData<String>()

    fun login(){

    }

}
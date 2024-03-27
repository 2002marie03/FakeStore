package com.baben.apps.appformation3.presentation.screens.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.baben.apps.appformation3.core.bases.BaseActivities
import com.baben.apps.appformation3.data.remote.repositories.ApiLoginRepository
import com.baben.apps.appformation3.databinding.ActivityLoginBinding
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.presentation.screens.home.HomeActivity
import com.baben.apps.appformation3.presentation.screens.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivities() {

    private lateinit var binding: ActivityLoginBinding


    lateinit var loginRepository: LoginRepository

     private val sharedPreferences: SharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActions()
    }

    private fun setupActions() {
        binding.uiLoginButton.setOnClickListener(::onLoginButtonClicked)
        binding.uiSignupButton.setOnClickListener(::onSignupButtonClicked)
    }

    private fun onLoginButtonClicked(view: View?) {
        val username = binding.uiUsernameEt.text.toString()
        val password = binding.uiPasswordEt.text.toString()

        val login = Login(username, password)

        // Make the login API call using injected repository
        val result = kotlinx.coroutines.runBlocking {
            loginRepository.login(login)
        }

        when (result) {
            LoginRepository.LoginResult.SUCCESS -> {
                // Login successful, handle user data and navigate to HomeActivity
                sharedPreferences.getBoolean("isLoggedIn", true)// Assuming a method to save login state
                startActivity(Intent(context, HomeActivity::class.java))
                finish()
            }
            LoginRepository.LoginResult.AUTH_ERROR -> {
                // Handle login failure (e.g., display error message)
                // ...
            }
            LoginRepository.LoginResult.GENERAL_ERROR -> {
                // Handle network error
                // ...
            }

            else -> {}
        }
    }

    private fun onSignupButtonClicked(view: View?) {
        startActivity(Intent(context, SignupActivity::class.java))
    }

}
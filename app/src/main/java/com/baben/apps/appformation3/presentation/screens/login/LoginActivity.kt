package com.baben.apps.appformation3.presentation.screens.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baben.apps.appformation3.core.bases.BaseActivities
import com.baben.apps.appformation3.data.remote.repositories.ApiLoginRepository
import com.baben.apps.appformation3.databinding.ActivityLoginBinding
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.presentation.screens.home.HomeActivity
import com.baben.apps.appformation3.presentation.screens.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivities() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var apiLoginRepo : ApiLoginRepository

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

    private  fun onLoginButtonClicked(view: View?) {
       // TODO("implement real login action")

        val email = binding.username.text.toString()
        val password = binding.passwrd.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            val loginModel = Login(email, password)
            val loginResult = kotlinx.coroutines.runBlocking {
                apiLoginRepo.login(loginModel)
            }

            when (loginResult) {
                LoginRepository.LoginResult.SUCCESS -> {
                    val sharedPref = getSharedPreferences("user_connexion_prefs", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("userName", "")
                    editor.apply()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                LoginRepository.LoginResult.AUTH_ERROR -> {
                }
                LoginRepository.LoginResult.GENERAL_ERROR -> {
                }
            }
        } else {
            Toast.makeText(this, "Veuillez saisir votre email et votre mot de passe", Toast.LENGTH_SHORT).show()
        }

    }

    private fun onSignupButtonClicked(view: View?) {
        startActivity(Intent(context, SignupActivity::class.java))
    }

}
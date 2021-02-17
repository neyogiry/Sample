package com.neyogiry.android.sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.neyogiry.android.sample.R
import com.neyogiry.android.sample.databinding.ActivityLoginBinding
import com.neyogiry.android.sample.util.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupEvents()
        setupObservers()
    }

    private fun setupEvents() {
        binding.user.doAfterTextChanged {
            loginViewModel.user.value = it.toString()
        }

        binding.password.doAfterTextChanged {
            loginViewModel.password.value = it.toString()
        }

        binding.login.setOnClickListener {
            loginViewModel.logIn()
        }

    }

    private fun setupObservers() {
        loginViewModel.isLoginEnabled.observe(this, Observer {
            binding.login.isEnabled = it
        })

        loginViewModel.loginResult.observe(this, Observer {
            if(it) toast(R.string.login_successful_message)
            else toast(R.string.login_failure_message)
        })
    }

}
package com.neyogiry.android.sample.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.neyogiry.android.sample.R
import com.neyogiry.android.sample.databinding.ActivityLoginBinding
import com.neyogiry.android.sample.util.SharedPreferencesHelper
import com.neyogiry.android.sample.util.toast
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private val sharedPreferencesHelper: SharedPreferencesHelper? by lazy {
        SharedPreferencesHelper.getDefaultSharedPreferences(this)?.let { SharedPreferencesHelper(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        validateSession()
        setupEvents()
        setupObservers()
    }

    private fun validateSession() {
        sharedPreferencesHelper?.getKey()?.let { toast("Next Activity") }
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
            if(it) {
                sharedPreferencesHelper?.saveKey("")
                toast(R.string.login_successful_message)
            }
            else toast(R.string.login_failure_message)
        })
    }

}
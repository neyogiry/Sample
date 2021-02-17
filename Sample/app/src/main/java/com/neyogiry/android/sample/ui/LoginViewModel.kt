package com.neyogiry.android.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val user = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _isLoginEnabled = MediatorLiveData<Boolean>().apply {
        addSource(user) { validateInputs() }
        addSource(password) { validateInputs() }
    }
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private fun validateInputs() {
        val isValid = user.value?.let { it.trim().isNotEmpty() } ?: false && password.value?.let { it.isNotEmpty() } ?: false
        _isLoginEnabled.postValue(isValid)
    }

    fun logIn() {
        val isValid =
            user.value?.let { it == "Admin" } == true &&
                    password.value?.let { it == "Password*123" } == true

        
        _loginResult.postValue(isValid)
    }

}
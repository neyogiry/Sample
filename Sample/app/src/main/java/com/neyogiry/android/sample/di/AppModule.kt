package com.neyogiry.android.sample.di

import com.neyogiry.android.sample.ui.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { LoginViewModel() }

}
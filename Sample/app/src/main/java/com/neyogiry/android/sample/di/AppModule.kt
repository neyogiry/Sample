package com.neyogiry.android.sample.di

import com.neyogiry.android.sample.data.api.ApiClient
import com.neyogiry.android.sample.data.api.ApiService
import com.neyogiry.android.sample.ui.login.LoginViewModel
import com.neyogiry.android.sample.ui.movies.MoviesViewModel
import com.neyogiry.android.sample.util.SharedPreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { SharedPreferencesHelper.getDefaultSharedPreferences(androidContext())?.let { SharedPreferencesHelper(it) } }

    single { SharedPreferencesHelper.getDefaultSharedPreferences(androidContext())?.let { SharedPreferencesHelper(it).getKey() ?: "" } }

    single { ApiClient.getApiService() }

    viewModel { LoginViewModel() }

    viewModel { MoviesViewModel(get(), get()) }

}
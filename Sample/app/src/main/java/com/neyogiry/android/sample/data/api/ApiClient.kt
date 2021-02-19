package com.neyogiry.android.sample.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val TAG = "ApiClient"

    private const val PATH = "https://api.themoviedb.org/3/"

    private const val READ_TIMEOUT: Long = 60
    private const val CONNECT_TIMEOUT: Long = 60

    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(PATH)
            .client(getBasicClientInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun getBasicClientInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(logging)
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }

}
package com.neyogiry.android.sample.data

import com.google.gson.Gson
import com.neyogiry.android.sample.data.api.response.BaseErrorResponse
import okhttp3.ResponseBody

object BaseErrorUtil {

    const val BASE_ERROR = "An error has occurred"

    fun message(errorBody: ResponseBody?): String {
        return errorBody?.let {
            val errorResponse = Gson().fromJson(it.string(), BaseErrorResponse::class.java)
            errorResponse.message ?: BASE_ERROR
        } ?: BASE_ERROR
    }

}
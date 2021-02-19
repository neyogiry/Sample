package com.neyogiry.android.sample.data.api.response

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse (
    @SerializedName("status_code") val code: Int? = 0,
    @SerializedName("status_message") val message: String? = null,
    @SerializedName("success") val success: Boolean? = false,
)
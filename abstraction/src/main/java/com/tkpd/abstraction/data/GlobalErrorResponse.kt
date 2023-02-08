package com.tkpd.abstraction.data

import com.google.gson.annotations.SerializedName

data class GlobalErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int = -1,
    @SerializedName("status_message")
    val statusMessage: String = ""
)
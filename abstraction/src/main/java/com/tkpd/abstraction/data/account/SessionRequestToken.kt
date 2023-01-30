package com.tkpd.abstraction.data.account

import com.google.gson.annotations.SerializedName

data class SessionRequestToken(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("expires_at")
    val expiredAt: String = "",
    @SerializedName("request_token")
    val requestToken: String = ""
)

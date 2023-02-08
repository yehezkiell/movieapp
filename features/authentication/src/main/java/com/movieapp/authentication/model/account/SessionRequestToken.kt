package com.movieapp.authentication.model.account

import com.google.gson.annotations.SerializedName

data class SessionRequestToken(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("expires_at")
    val expiredAt: String = "",
    @SerializedName("request_token")
    val requestToken: String = ""
)

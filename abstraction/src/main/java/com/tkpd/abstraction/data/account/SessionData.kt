package com.tkpd.abstraction.data.account

import com.google.gson.annotations.SerializedName

data class SessionData(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("session_id")
    val sessionId: String = ""
)

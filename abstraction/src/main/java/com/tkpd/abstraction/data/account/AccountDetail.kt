package com.tkpd.abstraction.data.account

import com.google.gson.annotations.SerializedName

data class AccountDetail(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("iso_639_1")
    val iso639: String = "",
    @SerializedName("iso_3166_1")
    val iso3166: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("include_adult")
    val includeAdult: Boolean = false,
    @SerializedName("username")
    val userName: String = "",
    @SerializedName("avatar")
    val avatar: Avatar = Avatar(),
)

data class Avatar(
    @SerializedName("gravatar")
    val avatar: Gravatar = Gravatar(),
    @SerializedName("tmdb")
    val tmdb: Tmdb = Tmdb()
)

data class Gravatar(
    @SerializedName("hash")
    val hash: String = ""
)

data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: String = ""
)

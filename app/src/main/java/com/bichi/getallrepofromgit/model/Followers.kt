package com.bichi.getallrepofromgit.model

import com.google.gson.annotations.SerializedName

data class Followers(
    val login:String,
    @SerializedName("avatar_url")
    val avatarUrl:String
)
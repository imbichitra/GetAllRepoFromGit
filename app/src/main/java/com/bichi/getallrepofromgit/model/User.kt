package com.bichi.getallrepofromgit.model


import com.google.gson.annotations.SerializedName

data class User(
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    val language: String,
    val name: String,
    val owner: Owner,
    @SerializedName("private")
    val isPrivate: Boolean
)
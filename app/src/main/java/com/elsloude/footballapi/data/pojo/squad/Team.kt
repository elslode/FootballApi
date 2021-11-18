package com.elsloude.footballapi.data.pojo.squad

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("logo")
    val logo: String
)
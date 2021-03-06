package com.elsloude.footballapi.data.pojo.squad

import com.google.gson.annotations.SerializedName

data class PlayerSquad(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("position")
    val position: String,
    @SerializedName("photo")
    val photo: String
)
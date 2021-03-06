package com.elsloude.footballapi.data.pojo.player

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("birth")
    val birth: Birth,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("weight")
    val weight: String ,
    @SerializedName("injured")
    val injured: Boolean,
    @SerializedName("photo")
    val photo: String,
)

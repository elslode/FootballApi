package com.elsloude.footballapi.data.pojo.player

import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("total")
    val total: Int,
)

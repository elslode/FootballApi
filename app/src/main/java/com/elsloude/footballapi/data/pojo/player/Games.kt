package com.elsloude.footballapi.data.pojo.player

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("appearences")
    val appearences: Int,
    @SerializedName("minutes")
    val minutes: Int,
    @SerializedName("rating")
    val rating: String
)

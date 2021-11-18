package com.elsloude.footballapi.data.pojo.player

import com.google.gson.annotations.SerializedName

data class ResponsePlayer(
    @SerializedName("response")
    val response: List<PlayerInfo>
)

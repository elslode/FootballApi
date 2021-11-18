package com.elsloude.footballapi.data.pojo.league

import com.google.gson.annotations.SerializedName

data class ResponseLeague(
    @SerializedName("response")
    val response: List<Int>
)

package com.elsloude.footballapi.data.pojo.squad

import com.google.gson.annotations.SerializedName

data class ResponseSquad(
    @SerializedName("response")
    val response: List<AllCommand>
)
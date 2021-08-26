package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CurrencyCourseResponse(
    @SerializedName("charCode") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("course") val course: String,
    @SerializedName("isUp") val isUp: Boolean
)
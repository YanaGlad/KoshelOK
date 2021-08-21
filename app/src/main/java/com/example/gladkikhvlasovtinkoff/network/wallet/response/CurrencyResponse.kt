package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CurrencyResponse (
    @SerializedName("id") val id : Long,
    @SerializedName("num_code") val numCode : String,
    @SerializedName("char_code") val code : String,
    @SerializedName("nominal") val nominal : String,
    @SerializedName("name") val name : String,
    @SerializedName("value") val value : String
)
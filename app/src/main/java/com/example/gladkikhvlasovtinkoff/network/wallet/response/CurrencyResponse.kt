package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CurrencyResponse (
    @SerializedName("charCode") val code : String,
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("nominal") val nominal : String,
    @SerializedName("numCode") val numCode : String,
    @SerializedName("value") val value : String
)
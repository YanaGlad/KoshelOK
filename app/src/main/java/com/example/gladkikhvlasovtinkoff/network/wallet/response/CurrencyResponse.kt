package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CurrencyResponse (
    @SerializedName("charCode") val code : String,
    @SerializedName("name") val name : String,
)

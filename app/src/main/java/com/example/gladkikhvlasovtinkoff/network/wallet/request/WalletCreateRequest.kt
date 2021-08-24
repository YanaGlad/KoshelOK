package com.example.gladkikhvlasovtinkoff.network.wallet.request

import com.google.gson.annotations.SerializedName

class WalletCreateRequest(
    @SerializedName("currencyCharCode") val currencyCharCode: String,
    @SerializedName("lim") val limit: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
)

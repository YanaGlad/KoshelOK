package com.example.gladkikhvlasovtinkoff.network.wallet.request

import com.google.gson.annotations.SerializedName

class WalletUpdateRequest(
    @SerializedName("hidden") val hidden: Boolean,
    @SerializedName("lim") val limit: String,
    @SerializedName("name") val name: String,
)

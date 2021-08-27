package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class WalletResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("currency") val currency: CurrencyResponse,
    @SerializedName("balance") val balance: String,
    @SerializedName("hidden") val isHidden: Boolean,
    @SerializedName("lim") val limit: String,
    @SerializedName("user") val user: UserResponse,
)
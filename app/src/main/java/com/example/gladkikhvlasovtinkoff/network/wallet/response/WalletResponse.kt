package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class WalletResponse (
        @SerializedName("balance") val balance : String,
        @SerializedName("currency") val currency : CurrencyResponse,
        @SerializedName("id") val id : Long,
        @SerializedName("name") val name : String,
        @SerializedName("userId") val userId  : Long,
        @SerializedName("limit") val limit : String
        )
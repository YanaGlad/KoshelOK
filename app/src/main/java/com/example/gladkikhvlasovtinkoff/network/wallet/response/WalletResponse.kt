package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class WalletResponse (
        @SerializedName("balance") val balance : String,
        @SerializedName("currency") val currency : CurrencyResponse,
        @SerializedName("hidden") val isHidden : Boolean,
        @SerializedName("id") val id : Long,
        @SerializedName("lim") val limit : String,
        @SerializedName("name") val name : String,
        @SerializedName("user") val user  : UserResponse,

)
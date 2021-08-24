package com.example.gladkikhvlasovtinkoff.network.wallet

import com.google.gson.annotations.SerializedName

class WalletRequest (
    @SerializedName("balance") val amount : String,
    @SerializedName("currency") val currency : CurrencyRequest,
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("user") val user : String,
)
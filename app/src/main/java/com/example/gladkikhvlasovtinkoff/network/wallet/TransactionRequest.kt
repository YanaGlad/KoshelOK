package com.example.gladkikhvlasovtinkoff.network.wallet

import com.google.gson.annotations.SerializedName

class TransactionRequest (
    @SerializedName("amount") val amount : String,
    @SerializedName("category") val category : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("date") val date : String,
    @SerializedName("id") val id : String,
    @SerializedName("income") val income : String,
    @SerializedName("wallet") val wallet : String,
    @SerializedName("walletCurrency") val walletCurrency : String,
)
package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class TransactionResponse(
    @SerializedName("amount") val amount: String,
    @SerializedName("category") val category: CategoryResponse,
    @SerializedName("local_date_time") val date: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("income") val income: Boolean,
    @SerializedName("transactionCurrencyCode") val code: String,
    @SerializedName("walletId") val walletId: Long
)
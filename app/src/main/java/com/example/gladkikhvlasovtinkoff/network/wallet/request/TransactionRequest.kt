package com.example.gladkikhvlasovtinkoff.network.wallet.request

import com.google.gson.annotations.SerializedName
import java.util.*

class  TransactionRequest(
    @SerializedName("amount") val amount: String,
    @SerializedName("category") val categoryRequest: CategoryRequest,
    @SerializedName("date") val date: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("income") val isIncome: Boolean,
    @SerializedName("transactionCurrencyCode") val transactionCurrencyCode: String,
    @SerializedName("walletId") val walletId: Long
)
package com.example.gladkikhvlasovtinkoff.network.wallet.request

import com.google.gson.annotations.SerializedName
import java.util.*

class TransactionRequest(
    @SerializedName("amount") val amount: String,
    @SerializedName("categoryId") val categoryId: Long,
    @SerializedName("date") val date: Long,
    @SerializedName("income") val isIncome: Boolean,
    @SerializedName("transactionCurrencyCode") val transactionCurrencyCode: String,
    @SerializedName("walletId") val walletId: Long
)

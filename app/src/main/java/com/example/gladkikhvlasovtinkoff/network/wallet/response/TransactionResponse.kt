package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.example.gladkikhvlasovtinkoff.model.Currency
import com.google.gson.annotations.SerializedName

class TransactionResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("wallet") val wallet: WalletResponse,
    @SerializedName("date") val date: Long,
    @SerializedName("amount") val amount: String,
    @SerializedName("currency") val currency: CurrencyResponse,
    @SerializedName("category") val category: CategoryResponse,
    @SerializedName("income") val income: Boolean,
)



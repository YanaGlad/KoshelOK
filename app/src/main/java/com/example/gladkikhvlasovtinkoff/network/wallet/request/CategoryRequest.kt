package com.example.gladkikhvlasovtinkoff.network.wallet.request

import com.google.gson.annotations.SerializedName

class  CategoryRequest(
    @SerializedName("b") val blue: Int,
    @SerializedName("description") val type: String,
    @SerializedName("g") val green: Int,
    @SerializedName("income") val isIncome: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("r") val red: Int,
    @SerializedName("stringId") val stringId: String
)
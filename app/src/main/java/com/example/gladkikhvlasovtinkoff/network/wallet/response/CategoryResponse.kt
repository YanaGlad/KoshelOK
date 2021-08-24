package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("stringId") val stringId: String,
    @SerializedName("description") val description: String,
    @SerializedName("income") val income: Boolean,
    @SerializedName("r") val redColor: Int,
    @SerializedName("g") val greenColor: Int,
    @SerializedName("b") val blueColor: Int
)
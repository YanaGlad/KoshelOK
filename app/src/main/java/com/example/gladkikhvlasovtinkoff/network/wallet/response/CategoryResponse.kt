package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("user") val user: UserResponse?,
    @SerializedName("name") val name: String?,
    @SerializedName("stringId") val stringId: String,
    @SerializedName("income") val income: Boolean,
    @SerializedName("id") val id: Long,
    @SerializedName("r") val redColor: Int,
    @SerializedName("g") val greenColor: Int,
    @SerializedName("b") val blueColor: Int
)

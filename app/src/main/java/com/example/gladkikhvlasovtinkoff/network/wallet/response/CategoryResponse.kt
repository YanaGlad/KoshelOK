package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName

class CategoryResponse (
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
    @SerializedName("imageUrl") val imageID : String,
    @SerializedName("description") val description : String,
    @SerializedName("income") val income : Boolean
    )

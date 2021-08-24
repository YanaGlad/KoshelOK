package com.example.gladkikhvlasovtinkoff.network.wallet.request

import com.google.gson.annotations.SerializedName

class UserRequest (
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String
    )
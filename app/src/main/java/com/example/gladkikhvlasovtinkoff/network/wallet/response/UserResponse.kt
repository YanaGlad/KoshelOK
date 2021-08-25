package com.example.gladkikhvlasovtinkoff.network.wallet.response

import com.google.gson.annotations.SerializedName


class UserResponse (
    @SerializedName("name") val name : String,
    @SerializedName("username")  val username : String
    )
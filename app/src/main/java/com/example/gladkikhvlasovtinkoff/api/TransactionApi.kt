package com.example.gladkikhvlasovtinkoff.api

import com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation.WalletOperationModel
import retrofit2.Call
import retrofit2.http.GET

interface TransactionApi {
    @GET("/something?json=true")
    fun getTransaction(): Call<WalletOperationModel>
}
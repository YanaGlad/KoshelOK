package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.network.wallet.response.WalletResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionApi {

    @GET("wallet/v1/find/{id}")
    fun findWalletById(@Path("id") walletId : Long) : Single<WalletResponse>

    @GET("wallet/v1/findUser/{userId}")
    fun getAllWalletsByUserId(@Path("userId") userId : Long) : Single<List<WalletResponse>>


}
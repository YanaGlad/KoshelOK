package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.response.WalletResponse
import io.reactivex.Single
import retrofit2.http.*

interface  TransactionApi {
    @GET("wallet/v1/find/{id}")
    fun findWalletById(@Path("id") walletId : Long) : Single<WalletResponse>

    @GET("wallet/v1/findUser/{userId}")
    fun getAllWalletsByUserId(@Path("userId") userId : Long) : Single<List<WalletResponse>>
 
    @POST("wallet/v1/crete")
    fun createWallet( @Field("id")  walletId : Long) : Single<WalletData>
 
    @POST("wallet/v1/crete")
    fun createWallet( @Field("id")  walletId : Long) : Single<WalletData>
 
    @POST("wallet/v1/delete/{id}")
    fun deleteWallet(@Body walletResponse: WalletResponse)
 
    @POST("wallet/v1/crete")
    fun createWallet( @Field("id")  walletId : Long) : Single<WalletData>

    @POST("wallet/v1/delete/{id}")
    fun deleteWallet(@Body walletResponse: WalletResponse)

    @POST("wallet/v1/wallet/v1/update-name")
    fun updateWallet(@Field("id")  walletId : Long, @Field("name")  name : String): Single<WalletData>
 

}
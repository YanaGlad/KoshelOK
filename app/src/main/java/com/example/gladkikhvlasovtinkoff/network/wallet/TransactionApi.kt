package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.request.TransactionRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletCreateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletUpdateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.CurrencyResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.TransactionResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.WalletResponse
import io.reactivex.Single
import retrofit2.http.*

interface  TransactionApi {

    @GET("/user/v1/findByUsername/{username}")
    fun findUserByUsername(@Path("username") username : String) : Single<List<UserResponse>>

    @POST("/user/v1/create")
    fun createUser(@Body userRequest: UserRequest) : Single<UserResponse>

    @GET("/currency/v1/findAll")
    fun getAllCurrencies() : Single<List<CurrencyResponse>>

    @POST("/wallet/v1/create")
    fun createWallet(@Body walletCreateRequest: WalletCreateRequest) : Single<WalletResponse>

    @POST("/wallet/v1/delete/{id}")
    fun deleteWallet(@Path("id") walletId : Long) : Single<WalletResponse>

    @GET("/wallet/v1/find/{id}")
    fun findWalletById(@Path("id") walletId : Long) : Single<WalletResponse>

    @GET("/wallet/v1/findUser/{username}")
    fun getAllWalletsByUsername(@Path("username") username : String) : Single<List<WalletResponse>>

    @POST("/wallet/v1/update")
    fun updateWallet(@Body walletUpdateRequest: WalletUpdateRequest): Single<WalletResponse>

    @POST("/transaction/v1/create")
    fun createTransaction(@Body transactionRequest: TransactionRequest): Single<TransactionResponse>

}
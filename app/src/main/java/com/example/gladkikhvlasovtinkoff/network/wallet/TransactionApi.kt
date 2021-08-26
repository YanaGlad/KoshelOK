package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.network.wallet.request.*
import com.example.gladkikhvlasovtinkoff.network.wallet.response.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("/wallet/v1/findByUsername/{username}")
    fun getAllWalletsByUsername(@Path("username") username : String) : Single<List<WalletResponse>>

    @POST("/wallet/v1/update")
    fun updateWallet(@Body walletUpdateRequest: WalletUpdateRequest): Single<WalletResponse>

    @POST("/transaction/v1/create")
    fun createTransaction(@Body transactionRequest: TransactionRequest): Single<TransactionResponse>

    @POST("/category/v1/create")
    fun createCategory(@Body categoryRequest: CategoryRequest): Single<CategoryResponse>

    @GET("/category/v1/findAllByUsername/{username}")
    fun getAllCategoriesByUsername(@Path("username") username : String) : Single<List<CategoryResponse>>

}
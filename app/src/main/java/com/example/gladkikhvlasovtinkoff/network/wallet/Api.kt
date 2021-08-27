package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.network.wallet.request.*
import com.example.gladkikhvlasovtinkoff.network.wallet.response.*
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @GET("/currency/v1/findByCharCode/{charCode}")
    fun getCurrencyCourse(@Path("charCode") code : String) : Single<CurrencyCourseResponse>

    @GET("/user/v1/findByUsername/{username}")
    fun findUserByUsername(@Path("username") username: String): Single<List<UserResponse>>

    @POST("/user/v1/create")
    fun createUser(@Body userRequest: UserRequest): Single<UserResponse>

    @GET("/currency/v1/findAll")
    fun getAllCurrencies(): Single<List<CurrencyResponse>>

    @POST("/wallet/v1/create")
    fun createWallet(@Body walletCreateRequest: WalletCreateRequest): Single<WalletResponse>

    @DELETE("/wallet/v1/delete/{id}")
    fun deleteWallet(@Path("id") walletId: Long): Single<Boolean>

    @GET("/wallet/v1/find/{id}")
    fun findWalletById(@Path("id") walletId: Long): Single<WalletResponse>

    @GET("/wallet/v1/findByUsername/{username}")
    fun getAllWalletsByUsername(@Path("username") username: String): Single<List<WalletResponse>>

    @PUT("/wallet/v1/update")
    fun updateWallet(
        @Body walletUpdateRequest: WalletUpdateRequest,
        @Query("id") walletId: Long
    ): Single<WalletResponse>

    @POST("/transaction/v1/create")
    fun createTransaction(@Body transactionRequest: TransactionRequest): Single<TransactionResponse>

    @POST("/category/v1/create")
    fun createCategory(@Body categoryRequest: CategoryRequest): Single<CategoryResponse>

    @GET("/category/v1/findAllByUsername/{username}")
    fun getAllCategoriesByUsername(@Path("username") username: String): Single<List<CategoryResponse>>

    @POST("/category/v1/create")
    fun getAllWalletsBalance(
        @Path("id") currencyCharCode: String,
        @Path("username") username : String
    ): Single<String>

    @GET("/transaction/v1/walletCountExpenses")
    fun getWalletExpensesCount(@Query("walletDtoId") walletId : Long) : Single<String>

    @GET("/transaction/v1/walletCountIncome")
    fun getWalletIncomeCount(@Query("walletDtoId") walletId : Long) : Single<String>

    @DELETE("/category/v1/delete/{id}")
    fun deleteCategory(@Path("id") id: Long): Single<Boolean>

    @POST("/transaction/v1/update")
    fun updateTransaction(@Body transactionRequest: TransactionRequest) : Single<TransactionResponse>

    @DELETE("/transaction/v1/delete/{id}")
    fun deleteTransaction(@Path("id") id : Long) : Single<Boolean>

    @GET("/transaction/v1/byWallet/{id}")
    fun getAllTransactions(@Path("id") walletId : Long) : Single<List<TransactionResponse>>

}
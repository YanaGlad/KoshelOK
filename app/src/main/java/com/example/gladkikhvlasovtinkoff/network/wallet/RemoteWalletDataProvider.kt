package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.network.wallet.request.*
import com.example.gladkikhvlasovtinkoff.network.wallet.response.CategoryResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.TransactionResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteWalletDataProvider {
    fun addUser(userRequest: UserRequest): Single<UserResponse>
    fun findUserByUsername(username: String): Single<List<UserResponse>>
    fun createCategory(categoryRequest: CategoryRequest): Single<CategoryDataSample>
    fun addUserWithAccount(account: GoogleSignInAccount): Single<UserResponse>
    fun getAllCurrencies(): Single<List<Currency>>
    fun findWalletById(walletId: Long): Single<WalletData>
    fun getAllWalletByUsername(username: String): Single<List<WalletData>>
    fun createWallet(walletRequest: WalletCreateRequest): Single<WalletData>
    fun deleteWallet(walletId: Long): Single<Boolean>
    fun updateWallet(walletUpdateRequest: WalletUpdateRequest, walletId: Long): Single<WalletData>
    fun createTransaction(transactionRequest: TransactionRequest): Single<WalletTransactionModel>
    fun getAllCategoriesByUsername(username: String): Single<List<CategoryDataSample>>
}


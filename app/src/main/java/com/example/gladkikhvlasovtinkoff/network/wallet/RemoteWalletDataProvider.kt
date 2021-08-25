package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.network.wallet.request.TransactionRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletCreateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletUpdateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Single

interface RemoteWalletDataProvider {
    fun addUser(userRequest: UserRequest) : Single<UserResponse>
    fun findUserByUsername(username : String) : Single<List<UserResponse>>

    fun addUserWithAccount(account: GoogleSignInAccount) : Single<UserResponse>
    fun getAllCurrencies() : Single<List<Currency>>
    fun findWalletById(walletId: Long): Single<WalletData>
    fun getAllWalletByUsername(username: String): Single<List<WalletData>>
    fun createWallet(walletRequest: WalletCreateRequest): Single<WalletData>
    fun deleteWallet(walletId: Long): Single<WalletData>
    fun updateWallet(walletUpdateRequest: WalletUpdateRequest): Single<WalletData>
    fun createTransaction(transactionRequest: TransactionRequest): Single<WalletTransactionModel>

}


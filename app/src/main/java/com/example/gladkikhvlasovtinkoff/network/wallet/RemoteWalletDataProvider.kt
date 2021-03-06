package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.*
import com.example.gladkikhvlasovtinkoff.network.wallet.request.*
import com.example.gladkikhvlasovtinkoff.network.wallet.response.TransactionResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Single

interface RemoteWalletDataProvider {
    fun addUser(userRequest: UserRequest): Single<UserResponse>
    fun findUserByUsername(username: String): Single<List<UserResponse>>
    fun createCategory(categoryRequest: CategoryRequest): Single<CategoryDataSample>
    fun addUserWithAccount(account: GoogleSignInAccount): Single<UserResponse>
    fun getAllCurrencies(): Single<List<Currency>>
    fun getCurrencyCourse(code : String) : Single<CurrencyCourse>
    fun findWalletById(walletId: Long): Single<WalletData>
    fun getAllWalletByUsername(username: String): Single<List<WalletData>>
    fun createWallet(walletRequest: WalletCreateRequest): Single<WalletData>
    fun deleteWallet(walletId: Long): Single<Boolean>
    fun updateWallet(walletUpdateRequest: WalletUpdateRequest, walletId: Long): Single<WalletData>
    fun createTransaction(transactionRequest: TransactionRequest): Single<WalletTransactionModel>
    fun getAllCategoriesByUsername(username: String): Single<List<CategoryDataSample>>
    fun getAllWalletsBalance(currencyCharCode: String, username : String ): Single<String>
    fun getExpensesByWallet(walletId: Long) : Single<String>
    fun getIncomeByWallet(walletId: Long) : Single<String>
    fun getAllExpenses(wallets: List<WalletData>) : Single<String>
    fun getAllIncome(wallets: List<WalletData>) : Single<String>
    fun deleteCategory(id: Long): Single<Boolean>
    fun updateTransaction(walletTransactionModel: WalletTransactionModel) : Single<TransactionResponse>
    fun deleteTransaction(id: Long) : Single<Boolean>
    fun loadAllTransactions(walletId: Long) : Single<List<WalletTransactionModel>>
}

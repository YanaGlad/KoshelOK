package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.model.BalanceInfo
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.TransactionListViewState
import io.reactivex.Flowable
import io.reactivex.Single

interface TransactionRepository {
    fun addTransaction(context : Context, item : WalletTransactionModel) : Single<TransactionListViewState>
    fun getAllTransactionsByWalletId(walletId : Long) : Flowable<List<WalletTransactionModel>>
    fun deleteTransaction(walletTransactionData : WalletTransactionModel) : Single<Boolean>
    fun updateTransaction(transaction : WalletTransactionModel) : Single<TransactionListViewState>
    fun getBalanceInfo(walletId: Long) : Single<BalanceInfo>
    fun loadAllTransactions(walletId: Long) : Single<List<WalletTransactionModel>>
}

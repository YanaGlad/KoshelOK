package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.model.BalanceInfo
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.TransactionListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import io.reactivex.Flowable
import io.reactivex.Single

interface TransactionRepository {
    fun addTransaction(context : Context, item : WalletTransactionModel) : Single<WalletListViewState>
    fun getAllTransactionsByWalletId(walletId : Long) : Flowable<List<WalletTransactionModel>>
    fun deleteTransaction(walletTransactionData : WalletTransactionModel) : Single<Boolean>
    fun updateTransaction(transaction : WalletTransactionModel) : Single<WalletTransactionModel>
    fun getBalanceInfo(walletId: Long) : Single<BalanceInfo>
    fun loadAllTransactions(walletId: Long) : Single<List<WalletTransactionModel>>
}
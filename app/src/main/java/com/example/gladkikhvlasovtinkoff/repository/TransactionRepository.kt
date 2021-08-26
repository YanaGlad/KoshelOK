package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.TransactionListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import io.reactivex.Flowable
import io.reactivex.Single

interface TransactionRepository {
    fun addTransaction(context : Context, item : WalletTransactionModel) : Single<WalletListViewState>
    fun addTransactions(items : List<TransactionListViewState>)
    fun getAllTransactionsByWalletId(walletId : Long) : Flowable<List<WalletTransactionModel>>
    fun getTransactionById(transactionId : Long) : Single<TransactionListViewState>
    fun deleteTransaction(item : TransactionListViewState)
    fun updateTransaction(item : TransactionListViewState)
}
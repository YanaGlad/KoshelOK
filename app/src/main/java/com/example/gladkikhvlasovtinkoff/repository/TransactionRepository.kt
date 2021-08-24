package com.example.gladkikhvlasovtinkoff.repository

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.TransactionListViewState
import io.reactivex.Flowable
import io.reactivex.Single

interface TransactionRepository {
    fun addTransaction(item : WalletTransactionModel)
    fun addTransactions(items : List<WalletTransactionModel>)
    fun getAllTransactionsByWalletId(walletId : Long) : Flowable< TransactionListViewState>
    fun getTransactionById(transactionId : Long) : Single<WalletTransactionModel>
    fun deleteTransaction(item : WalletTransactionModel)
    fun updateTransaction(item : WalletTransactionModel)
}
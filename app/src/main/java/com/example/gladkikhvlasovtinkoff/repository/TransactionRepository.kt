package com.example.gladkikhvlasovtinkoff.repository

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.TransactionListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface TransactionRepository {
    fun addTransaction(item : WalletTransactionModel) : Completable
    fun addTransactions(items : List<TransactionListViewState>)
   // fun getAllTransactionsByWalletId(walletId : Long) : Flowable< TransactionListViewState>
    fun getTransactionById(transactionId : Long) : Single<TransactionListViewState>
    fun deleteTransaction(item : TransactionListViewState)
    fun updateTransaction(item : TransactionListViewState)
}
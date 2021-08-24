package com.example.gladkikhvlasovtinkoff.db

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalTransactionDataProvider {
   // fun insertTransaction(item : WalletTransactionModel)
    fun insertTransactions(items : List<WalletTransactionModel>)
    //fun getAllTransactionsByWalletId(walletId : Long) : Flowable<List<WalletTransactionModel>>
    fun getTransactionById(transactionId : Long) : Single<WalletTransactionModel>
    fun deleteTransaction(item : WalletTransactionModel)
    fun updateTransaction(item : WalletTransactionModel)
}
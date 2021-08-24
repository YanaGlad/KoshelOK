package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.LocalTransactionDataProvider
 
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.TransactionListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
 
    private val transactionDataProvider: LocalTransactionDataProvider) :TransactionRepository {
    override fun addTransaction(item: WalletTransactionModel): Completable {
        TODO("Not yet implemented")
    }
 

    override fun addTransactions(items: List<TransactionListViewState>) {
        TODO("Not yet implemented")
    }

 
//    override fun getAllTransactionsByWalletId(walletId: Long): Flowable<TransactionListViewState> =
//        transactionDataProvider.getAllTransactionsByWalletId(walletId)
//            .map {
//                TransactionListViewState.Loaded(it)
//            }
 


    override fun getTransactionById(transactionId: Long): Single<TransactionListViewState> {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(item: TransactionListViewState) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(item: TransactionListViewState) {
        TODO("Not yet implemented")
    }
}

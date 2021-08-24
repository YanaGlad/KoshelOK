package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.db.LocalTransactionDataProvider
 
import com.example.gladkikhvlasovtinkoff.extension.toDelegateItemListWithDate
 
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.TransactionListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
 
    private val localDataProvider: LocalTransactionDataProvider) :TransactionRepository {

    override fun addTransaction(context : Context, item: WalletTransactionModel) =
        Completable.create{emitter ->
            localDataProvider.insertTransaction(context,item)
            emitter.onComplete()
        }

 

    override fun addTransactions(items: List<TransactionListViewState>) {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionsByWalletId(walletId: Long): Flowable<List<WalletTransactionModel>> =
        localDataProvider.getAllTransactionsByWalletId(walletId)



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

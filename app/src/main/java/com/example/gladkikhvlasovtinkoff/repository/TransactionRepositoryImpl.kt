package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.db.LocalTransactionDataProvider
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.TransactionRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.TransactionListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataProvider: LocalTransactionDataProvider,
    private val remoteTransactionDataProvider: RemoteWalletDataProvider
) : TransactionRepository {

    override fun addTransaction(context: Context, item: WalletTransactionModel) =

        remoteTransactionDataProvider.createTransaction(
            TransactionRequest(
                amount = item.amount,
//                categoryRequest = CategoryRequest(
//                    blue = item.transactionCategoryData.colorBlue,
//                    type = item.transactionCategoryData.description,
//                    green = item.transactionCategoryData.colorGreen,
//                    isIncome = item.isIncome,
//                    name = item.transactionCategoryData.name,
//                    red = item.transactionCategoryData.colorRed,
//                    stringId = item.transactionCategoryData.name
//                ),
                date = item.date,
                id = item.id,
                isIncome = item.isIncome,
                transactionCurrencyCode = item.currency.code,
                walletId = item.walletId
            )
        ).flatMapCompletable { data ->
            Completable.create { emitter ->
                try {
                    localDataProvider
                        .insertTransaction(context, data)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
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

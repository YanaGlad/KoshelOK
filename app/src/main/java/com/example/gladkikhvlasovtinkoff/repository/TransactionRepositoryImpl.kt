package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.LocalTransactionDataProvider
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.TransactionRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.TransactionListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataProvider: LocalTransactionDataProvider,
    private val remoteTransactionDataProvider: RemoteWalletDataProvider,
    private val authDataHolder: AuthDataHolder
) : TransactionRepository {

    override fun addTransaction(context: Context, item: WalletTransactionModel) =
        remoteTransactionDataProvider.createTransaction(
            TransactionRequest(
                amount = item.amount,
                categoryRequest = CategoryRequest(
                    blue = item.transactionCategoryData.colorBlue,
                    green = item.transactionCategoryData.colorGreen,
                    isIncome = item.isIncome,
                    name = item.transactionCategoryData.name,
                    red = item.transactionCategoryData.colorRed,
                    stringId = item.transactionCategoryData.name,
                    username = authDataHolder.getUserKey()
                ),
                date = item.date,
                id = item.id,
                isIncome = item.isIncome,
                transactionCurrencyCode = item.currency.code,
                walletId = item.walletId
            )
        )

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

package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.db.dataprovider.LocalTransactionDataProvider
import com.example.gladkikhvlasovtinkoff.extension.getIconIdByNameId
import com.example.gladkikhvlasovtinkoff.model.*
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.TransactionRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.TransactionListViewState
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataProvider: LocalTransactionDataProvider,
    private val remoteTransactionDataProvider: RemoteWalletDataProvider
) : TransactionRepository {

    override fun addTransaction(
        context: Context,
        item: WalletTransactionModel
    ): Single<TransactionListViewState> =
        Single.create { emitter ->
            val request = TransactionRequest(
                amount = item.amount,
                date = item.date,
                isIncome = item.isIncome,
                transactionCurrencyCode = item.currency.code,
                walletId = item.walletId,
                categoryId = item.transactionCategoryData.id
            )
            remoteTransactionDataProvider.createTransaction(request)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    { category ->
                        localDataProvider.insertTransaction(context, category)
                        emitter.onSuccess(TransactionListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }

    override fun getAllTransactionsByWalletId(walletId: Long): Flowable<List<WalletTransactionModel>> =
        localDataProvider.getAllTransactionsByWalletId(walletId)

    override fun deleteTransaction(walletTransactionData: WalletTransactionModel): Single<Boolean> =
        remoteTransactionDataProvider
            .deleteTransaction(walletTransactionData.id)
            .doOnSuccess { isDeleted ->
                if (isDeleted)
                    localDataProvider.deleteTransaction(
                        walletTransactionData
                    )
            }

    override fun updateTransaction(transaction: WalletTransactionModel): Single<TransactionListViewState> =
        Single.create { emitter ->
            remoteTransactionDataProvider
                .updateTransaction(
                    WalletTransactionModel(
                        transaction.id,
                        transaction.date,
                        transaction.walletId,
                        transaction.isIncome,
                        transaction.amount,
                        transaction.currency,
                        transaction.transactionCategoryData
                    )
                )
                .subscribe(
                    { tr ->
                        localDataProvider.updateTransaction(
                            WalletTransactionModel(
                                tr.id,
                                tr.date,
                                tr.wallet.id,
                                tr.income,
                                tr.amount,
                                Currency(tr.currency.code, tr.currency.name),
                                TransactionCategoryData(
                                    tr.category.id,
                                    UNDEFINED_STR,
                                    getIconIdByNameId(tr.category.stringId),
                                    tr.wallet.user.username,
                                    tr.wallet.name,
                                    tr.category.redColor,
                                    tr.category.blueColor,
                                    tr.category.greenColor,
                                    tr.income
                                )
                            )
                        )
                        emitter.onSuccess(TransactionListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }

    override fun getBalanceInfo(walletId: Long): Single<BalanceInfo> =
        remoteTransactionDataProvider
            .getIncomeByWallet(walletId)
            .zipWith(
                remoteTransactionDataProvider
                    .getExpensesByWallet(walletId),
                { income, expenses ->
                    BalanceInfo(
                        expenses = expenses,
                        income = income
                    )
                }
            )

    override fun loadAllTransactions(walletId: Long) =
        remoteTransactionDataProvider
            .loadAllTransactions(walletId)
            .doOnSuccess{
                localDataProvider.insertTransactions(it)
            }


    private fun Throwable.convertToViewState(): TransactionListViewState =
        when (this) {
            is IOException -> TransactionListViewState.Error.NetworkError
            else -> TransactionListViewState.Error.UnexpectedError
        }
}

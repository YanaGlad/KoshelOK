package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import android.util.Log
import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.dataprovider.LocalTransactionDataProvider
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.TransactionRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.TransactionListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val localDataProvider: LocalTransactionDataProvider,
    private val remoteTransactionDataProvider: RemoteWalletDataProvider,
    private val authDataHolder: AuthDataHolder
) : TransactionRepository {

    override fun addTransaction(context: Context, item: WalletTransactionModel): Single<WalletListViewState> =
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
                        emitter.onSuccess(WalletListViewState.SuccessOperation)
                        Log.d("AAA99", "YAY")
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                        Log.d("AAA99", "No...")
                    }
                )
        }

    override fun getAllTransactionsByWalletId(walletId: Long): Flowable<List<WalletTransactionModel>> =
        localDataProvider.getAllTransactionsByWalletId(walletId)

    override fun deleteTransaction(walletTransactionModel: WalletTransactionModel)  : Single<Boolean> =
        remoteTransactionDataProvider
            .deleteTransaction(walletTransactionModel.id)
            .doOnSuccess{ isDeleted ->
                if(isDeleted)
                    localDataProvider.deleteTransaction(
                        walletTransactionModel
                    )
            }

    override fun updateTransaction(transaction: WalletTransactionModel) : Single<WalletTransactionModel> = TODO()
//        remoteTransactionDataProvider
//            .updateTransaction(transaction)


    private fun Throwable.convertToViewState(): WalletListViewState =
        when (this) {
            is IOException -> WalletListViewState.Error.NetworkError
            else -> WalletListViewState.Error.UnexpectedError
        }
}

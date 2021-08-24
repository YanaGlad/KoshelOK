package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomTransactionDataProvider @Inject constructor(val dao : TransactionDao)
    : LocalTransactionDataProvider {

    override fun insertTransaction(item: WalletTransactionModel) {
        TODO("Not yet implemented")
    }

    override fun insertTransactions(items: List<WalletTransactionModel>) {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionsByWalletId(walletId: Long): Flowable<List<WalletTransactionModel>> {
        return dao.getAllTransactionsByWalletId(walletId)
            .map { transitions->
                transitions
                    .map {
                        transition ->
                        WalletTransactionModel(
                            date = transition.date,
                            walletId = transition.walletId,
                            isIncome = transition.isIncome,
                            amount = transition.amount,
                            currency = Currency(
                                id = transition.currency.id,
                                code = transition.currency.code,
                                name = transition.currency.name
                            )

                        )
                    }

            }
    }

    override fun getTransactionById(transactionId: Long): Single<WalletTransactionModel> {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(item: WalletTransactionModel) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(item: WalletTransactionModel) {
        TODO("Not yet implemented")
    }

}

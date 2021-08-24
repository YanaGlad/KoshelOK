package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomTransactionDataProvider @Inject constructor(val dao : TransactionDao)
    : LocalTransactionDataProvider {
 
    override fun insertTransaction(context : Context, item : WalletTransactionModel) =
        dao.insertTransaction(
            TransactionDB(
                id = item.id,
                date = item.date,
                walletId = item.walletId,
                isIncome = item.isIncome,
                amount = item.amount,
                currency = CurrencyDB(
                    code = item.currency.code,
                    name = item.currency.name
                ),
                transactionCategoryData = CategoryDB(
                    id = item.transactionCategoryData.id,
                    name = item.transactionCategoryData.name,
                    stringId = getIdentifierForCategoryName(
                       context,  item.transactionCategoryData.name
                    ).name,
                    description = item.transactionCategoryData.description,
                    colorRed = item.transactionCategoryData.colorRed,
                    colorBlue = item.transactionCategoryData.colorBlue,
                    colorGreen = item.transactionCategoryData.colorGreen
                    )
            )
        )
 
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

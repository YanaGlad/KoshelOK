package com.example.gladkikhvlasovtinkoff.db


import android.content.Context
import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel

import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.extension.getIconIdByNameId
import com.example.gladkikhvlasovtinkoff.extension.getIdentifierForCategoryName
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import io.reactivex.Flowable

import io.reactivex.Single
import javax.inject.Inject

class RoomTransactionDataProvider @Inject constructor(val dao: TransactionDao) :
    LocalTransactionDataProvider {

    override fun insertTransaction(context: Context, item: WalletTransactionModel) =
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
                    username = item.transactionCategoryData.userName,
                    name = item.transactionCategoryData.name,
                    stringId = getIdentifierForCategoryName(
                        context, item.transactionCategoryData.name
                    ).name,
                    description = item.transactionCategoryData.description,
                    colorRed = item.transactionCategoryData.colorRed,
                    colorBlue = item.transactionCategoryData.colorBlue,
                    colorGreen = item.transactionCategoryData.colorGreen,
                    income = item.isIncome,
                    id = item.id
                )
            )
        )


    override fun insertTransactions(items: List<WalletTransactionModel>) {
        TODO("Not yet implemented")
    }


    override fun getAllTransactionsByWalletId(walletId: Long): Flowable<List<WalletTransactionModel>> {
        return dao.getAllTransactionsByWalletId(walletId)
            .map { transitions ->
                transitions
                    .map { transition ->
                        WalletTransactionModel(
                            id = transition.id,
                            date = transition.date,
                            walletId = transition.walletId,
                            isIncome = transition.isIncome,
                            amount = transition.amount,
                            currency = Currency(
                                code = transition.currency.code,
                                name = transition.currency.name
                            ),
                            TransactionCategoryData(
                                userName = transition.transactionCategoryData.username,
                                name = transition.transactionCategoryData.name,
                                iconId = getIconIdByNameId(
                                    transition.transactionCategoryData.stringId
                                ),
                                description = transition.transactionCategoryData.description,
                                colorRed = transition.transactionCategoryData.colorRed,
                                colorBlue = transition.transactionCategoryData.colorBlue,
                                colorGreen = transition.transactionCategoryData.colorGreen,
                                income = transition.isIncome,
                                id = transition.id
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

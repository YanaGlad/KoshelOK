package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import com.example.gladkikhvlasovtinkoff.model.TransactionDate
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.DateDelegateItem
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.DelegateItem
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.TransactionDelegateItem

fun List<WalletTransactionModel>.toDelegateItemListWithDate(context: Context) : List<DelegateItem>{
    val delegateItemList: MutableList<DelegateItem> = mutableListOf()
    var lastDate = ""
    this
        .sortedByDescending { it.date }
        .forEach { transaction ->
            if (transaction.date.getDateString() != lastDate) {
                delegateItemList.add(
                    DateDelegateItem(
                        TransactionDate(transaction.date.getDayString(context))
                    )
                )
                delegateItemList.add(
                    TransactionDelegateItem(
                        transaction
                    )
                )
                lastDate = transaction.date.getDateString()
            } else {
                delegateItemList.add(
                    TransactionDelegateItem(
                        transaction
                    )
                )
            }
        }
    return delegateItemList
}
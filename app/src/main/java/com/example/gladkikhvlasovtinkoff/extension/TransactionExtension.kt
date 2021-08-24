package com.example.gladkikhvlasovtinkoff.extension

import com.example.gladkikhvlasovtinkoff.model.TransactionDate
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.DateDelegateItem
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.DelegateItem
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.TransactionDelegateItem


fun List<WalletTransactionModel>.toDelegateItemListWithDate() : List<DelegateItem>{
    val delegateItemList: MutableList<DelegateItem> = mutableListOf()
    var lastDate = ""
    this
        .sortedByDescending { it.date }
        .forEach { transaction ->
            if (transaction.date.getDateString() != lastDate) {
                delegateItemList.add(
                    DateDelegateItem(
                        TransactionDate(transaction.date)
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
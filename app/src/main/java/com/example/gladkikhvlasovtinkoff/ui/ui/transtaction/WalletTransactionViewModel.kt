package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.extension.MILLIS_IN_DAY
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel

class WalletTransactionViewModel : ViewModel() {
    var transactionList: MutableList<WalletTransactionModel> =
        mutableListOf()

    //получение списка из бд

    init {

        val list = ArrayList<WalletTransactionModel>()

        list.apply {
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis(),
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis(),
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis(),
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis(),
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis() - MILLIS_IN_DAY,
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 2,
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 3,
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 4,
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome =
                    false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 5,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    walletId = 0,
                    isIncome = false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
            add(
                WalletTransactionModel(
                    date = System.currentTimeMillis() - MILLIS_IN_DAY * 6,
                    walletId = 0,
                    transactionCategoryData =
                    TransactionCategoryData(
                        name = "chototam",
                        iconId = R.drawable.ic_gas_station,
                        id = 5,
                        description = "",
                        color = R.color.default_dot_color
                    ),
                    isIncome = false,
                    amount = "isgjsiog",
                    currency = Currency(
                        code = "au",
                        name = "rubli"
                    )
                )
            )
        }
        transactionList = list
    }
}
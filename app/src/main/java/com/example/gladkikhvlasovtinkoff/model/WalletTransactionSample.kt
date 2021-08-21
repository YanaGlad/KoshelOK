package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class WalletTransactionSample(
    var date: Long = UNDEFINED_ID.toLong(),
    var walletId : Long = UNDEFINED_ID.toLong(),
    var isIncome : Boolean = false,
    var amount : String = UNDEFINED_STR,
    var currency : Currency = Currency(UNDEFINED_STR, UNDEFINED_STR),
    var transactionCategoryData: TransactionCategoryData = TransactionCategoryData(UNDEFINED_STR,
        UNDEFINED_ID, UNDEFINED_ID, UNDEFINED_STR)
) : Parcelable {
    fun createModel(): WalletTransactionModel {
        return WalletTransactionModel(
            date = date,
            walletId = walletId,
            isIncome = isIncome,
            amount = amount,
            currency = currency,
            transactionCategoryData = transactionCategoryData
        )
    }

    val isDateDefined
    get() = date != UNDEFINED_ID.toLong()

    val isValid
        get() =
            date != UNDEFINED_ID.toLong() &&
                    amount != UNDEFINED_STR && currency.code != UNDEFINED_STR &&
                    currency.name != UNDEFINED_STR && transactionCategoryData.iconId != UNDEFINED_ID
                    && transactionCategoryData.name != UNDEFINED_STR
}
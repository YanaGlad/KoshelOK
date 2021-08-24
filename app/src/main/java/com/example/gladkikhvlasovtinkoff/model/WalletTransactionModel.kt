package com.example.gladkikhvlasovtinkoff.model


data class WalletTransactionModel (
    val id : Long = UNDEFINED_ID.toLong(),
    val date : Long = UNDEFINED_ID.toLong(),
    val walletId : Long = UNDEFINED_ID.toLong(),
    val isIncome : Boolean = false,
    val amount : String = UNDEFINED_STR,
    val currency: Currency = Currency(UNDEFINED_STR, UNDEFINED_STR),
    val transactionCategoryData: TransactionCategoryData = TransactionCategoryData(UNDEFINED_STR,
    UNDEFINED_ID, UNDEFINED_ID.toLong(), UNDEFINED_STR, UNDEFINED_ID,UNDEFINED_ID,UNDEFINED_ID)
    ){

    val isValid
    get() =
        date != UNDEFINED_ID.toLong() &&
                amount != UNDEFINED_STR && currency.code != UNDEFINED_STR &&
                currency.name != UNDEFINED_STR && transactionCategoryData.iconId != UNDEFINED_ID
                && transactionCategoryData.name != UNDEFINED_STR
}
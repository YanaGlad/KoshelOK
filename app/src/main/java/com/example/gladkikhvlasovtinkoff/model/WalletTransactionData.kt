package com.example.gladkikhvlasovtinkoff.model


data class WalletTransactionData (
    val date : Long = UNDEFINED_ID.toLong(),
    val walletId : Long = UNDEFINED_ID.toLong(),
    val isIncome : Boolean = false,
    val amount : String = UNDEFINED_STR,
    val currency: Currency = Currency(UNDEFINED_STR, UNDEFINED_STR),
    val operationCategoryData: OperationCategoryData = OperationCategoryData(UNDEFINED_STR,
    UNDEFINED_ID, UNDEFINED_ID, UNDEFINED_STR)
    ){

    val isValid
    get() =
        date != UNDEFINED_ID.toLong() && walletId != UNDEFINED_ID.toLong() &&
                amount != UNDEFINED_STR && currency.code != UNDEFINED_STR &&
                currency.name != UNDEFINED_STR && operationCategoryData.iconId != UNDEFINED_ID
                && operationCategoryData.name != UNDEFINED_STR
}
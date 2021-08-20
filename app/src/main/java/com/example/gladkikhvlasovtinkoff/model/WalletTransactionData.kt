package com.example.gladkikhvlasovtinkoff.model


data class WalletTransactionData (
    val date : Long,
    val walletId : Long,
    val isIncome : Boolean,
    val amount : String,
    val currency: Currency,
    val operationCategoryData: OperationCategoryData
    )
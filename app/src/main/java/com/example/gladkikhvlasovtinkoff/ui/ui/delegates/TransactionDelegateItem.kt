package com.example.gladkikhvlasovtinkoff.ui.ui.delegates

import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel

data class TransactionDelegateItem (
    val walletTransactionModel : WalletTransactionModel
        ) : DelegateItem{

    override fun content(): Any = walletTransactionModel

    //TODO - Исправить , возможна некорректная работа
    override fun id(): Any = walletTransactionModel.date
}
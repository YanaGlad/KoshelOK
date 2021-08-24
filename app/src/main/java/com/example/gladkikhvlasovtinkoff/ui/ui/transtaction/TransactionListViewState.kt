package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState

sealed class TransactionListViewState {
    object Loading : TransactionListViewState()

    class Loaded(val list : List<WalletTransactionModel>) : TransactionListViewState()

    class Error{
        object NetworkError : TransactionListViewState()

        object UnexpectedError : TransactionListViewState()
    }

    object SuccessOperation : TransactionListViewState()
}
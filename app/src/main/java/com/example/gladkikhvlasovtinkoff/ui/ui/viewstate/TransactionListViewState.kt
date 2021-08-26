package com.example.gladkikhvlasovtinkoff.ui.ui.viewstate

import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.DelegateItem

sealed class TransactionListViewState {
    object Loading : TransactionListViewState()

    class Loaded(val list : List<DelegateItem>) : TransactionListViewState()

    class Error{
        object NetworkError : TransactionListViewState()

        object UnexpectedError : TransactionListViewState()
    }

    object SuccessOperation : TransactionListViewState()
}
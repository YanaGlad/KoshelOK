package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState

sealed class CategoryListViewState {
    object Loading : CategoryListViewState()

    class Loaded(val list : List<TransactionCategoryData>) : CategoryListViewState()

    class Error{
        object NetworkError : CategoryListViewState()

        object UnexpectedError : CategoryListViewState()

        object AuthError : CategoryListViewState()
    }

    object SuccessOperation : CategoryListViewState()
}
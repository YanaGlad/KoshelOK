package com.example.gladkikhvlasovtinkoff.ui.ui.viewstate

import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData

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
package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

sealed class CategoryViewState {
    object Loading : CategoryViewState()
    object SuccessCreating: CategoryViewState()

    class Error(){
        object NetworkError : CategoryViewState()

        object UnexpectedError : CategoryViewState()
    }
}
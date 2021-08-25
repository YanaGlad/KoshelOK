package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.ConfirmCreatingViewState

sealed class CategoryViewState {
    object Loading : CategoryViewState()
    object SuccessCreating: CategoryViewState()

    class Error(){
        object NetworkError : CategoryViewState()

        object UnexpectedError : CategoryViewState()
    }
}
package com.example.gladkikhvlasovtinkoff.ui.ui.viewstate

sealed class CategoryViewState {
    object Loading : CategoryViewState()
    object SuccessCreating: CategoryViewState()

    class Error(){
        object NetworkError : CategoryViewState()

        object UnexpectedError : CategoryViewState()
    }
}
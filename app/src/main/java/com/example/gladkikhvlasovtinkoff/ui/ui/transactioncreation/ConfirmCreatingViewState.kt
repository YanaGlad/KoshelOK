package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

sealed class ConfirmCreatingViewState{
    object Loading : ConfirmCreatingViewState()
    object SuccessCreating: ConfirmCreatingViewState()

    class Error(){
        object NetworkError : ConfirmCreatingViewState()

        object UnexpectedError : ConfirmCreatingViewState()
    }
}
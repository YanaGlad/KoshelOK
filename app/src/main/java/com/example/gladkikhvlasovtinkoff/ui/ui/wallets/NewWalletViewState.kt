package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

sealed class NewWalletViewState{
    object Loading : NewWalletViewState()
    class Error {
        object NetworkError : NewWalletViewState()
        object UnexpectedError : NewWalletViewState()
    }
    object SuccessOperation : NewWalletViewState()
}

package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import com.example.gladkikhvlasovtinkoff.model.WalletData

sealed class WalletListViewState {
    object Loading : WalletListViewState()

    class Loaded(val list : List<WalletData>) : WalletListViewState()
    class LoadedString(val result: String) : WalletListViewState()

    class Error{
        object NetworkError : WalletListViewState()

        object UnexpectedError : WalletListViewState()

        object AuthError : WalletListViewState()
    }

    object SuccessOperation : WalletListViewState()
}
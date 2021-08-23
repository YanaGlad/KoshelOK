package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState


sealed class CurrencyListViewState {
    object Loading : CurrencyListViewState()

    class Loaded(val list : List<Currency>) : CurrencyListViewState()

    class Error{
        object NetworkError : CurrencyListViewState()

        object UnexpectedError : CurrencyListViewState()
    }

    object SuccessOperation : CurrencyListViewState()
}
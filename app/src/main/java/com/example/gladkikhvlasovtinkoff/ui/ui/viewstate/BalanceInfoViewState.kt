package com.example.gladkikhvlasovtinkoff.ui.ui.viewstate

import com.example.gladkikhvlasovtinkoff.model.BalanceInfo

sealed class BalanceInfoViewState{
    object Loading : BalanceInfoViewState()

    class Loaded(val userBalanceInfo: BalanceInfo) : BalanceInfoViewState()

    class Error{
        object NetworkError : BalanceInfoViewState()

        object UnexpectedError : BalanceInfoViewState()
    }
}

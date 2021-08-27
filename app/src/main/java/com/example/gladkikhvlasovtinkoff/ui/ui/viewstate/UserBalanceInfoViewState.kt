package com.example.gladkikhvlasovtinkoff.ui.ui.viewstate

import com.example.gladkikhvlasovtinkoff.model.BalanceInfo

sealed class UserBalanceInfoViewState{
    object Loading : UserBalanceInfoViewState()

    class Loaded(val userBalanceInfo: BalanceInfo) : UserBalanceInfoViewState()

    class Error{
        object NetworkError : UserBalanceInfoViewState()

        object UnexpectedError : UserBalanceInfoViewState()
    }
}

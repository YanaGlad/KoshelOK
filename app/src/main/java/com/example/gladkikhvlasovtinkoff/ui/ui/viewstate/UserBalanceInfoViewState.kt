package com.example.gladkikhvlasovtinkoff.ui.ui.viewstate

import com.example.gladkikhvlasovtinkoff.model.UserBalanceInfo

sealed class UserBalanceInfoViewState{
    object Loading : UserBalanceInfoViewState()

    class Loaded(val userBalanceInfo: UserBalanceInfo) : UserBalanceInfoViewState()

    class Error{
        object NetworkError : UserBalanceInfoViewState()

        object UnexpectedError : UserBalanceInfoViewState()
    }
}

package com.example.gladkikhvlasovtinkoff.ui.ui.welcome

sealed class AuthViewState {
    object SuccessLogin : AuthViewState()

    object Loading : AuthViewState()

    class Error{
        object NetworkError : AuthViewState()
        object UnexpectedError : AuthViewState()
    }
}
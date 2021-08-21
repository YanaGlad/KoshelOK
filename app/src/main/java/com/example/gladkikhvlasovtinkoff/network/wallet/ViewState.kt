package com.example.gladkikhvlasovtinkoff.network.wallet

sealed class ViewState<out T>{
    object Loading : ViewState<Nothing>()
    data class Error(val message: String): ViewState<Nothing>()
    data class Ok<T>(val data: T): ViewState<T>()
    data class NetworkError<T>(val data: T): ViewState<T>()
}

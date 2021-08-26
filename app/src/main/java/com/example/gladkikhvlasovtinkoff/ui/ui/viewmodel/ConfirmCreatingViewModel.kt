package com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.*
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.ConfirmCreatingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ConfirmCreatingViewModel @Inject constructor(
    val repository: TransactionRepository
) : ViewModel() {
    private val _viewState: MutableLiveData<ConfirmCreatingViewState> = MutableLiveData()
    val viewState: LiveData<ConfirmCreatingViewState>
        get() = _viewState

    fun addTransaction(context: Context, transaction : WalletTransactionSample) {
        _viewState.value = ConfirmCreatingViewState.Loading
        val disposable = repository.addTransaction(
            context = context,
            item = WalletTransactionModel(
                id = UNDEFINED_ID.toLong(),
                date = transaction.date,
                walletId = transaction.walletId,
                isIncome = transaction.isIncome,
                amount = transaction.amount,
                currency = Currency( transaction.currency.code, UNDEFINED_STR),
                transactionCategoryData = transaction.transactionCategoryData
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { model ->
                    Log.d("AAA99", "Here : here ")
                    _viewState.postValue(ConfirmCreatingViewState.SuccessCreating)
                },
                { e ->
                    Log.d("AAA99", "Error : here  ")
                    e.printStackTrace()
                    _viewState.postValue(e.convertToViewState())
                }
            )
    }

    private fun Throwable.convertToViewState() =
        when (this){
            is IOException -> ConfirmCreatingViewState.Error.NetworkError
            else  -> ConfirmCreatingViewState.Error.UnexpectedError
        }
}
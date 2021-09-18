package com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_STR
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
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
                id = transaction.transactionCategoryData.id,
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
                {
                    _viewState.postValue(ConfirmCreatingViewState.SuccessCreating)
                },
                { e ->
                    _viewState.postValue(e.convertToViewState())
                }
            )
    }

    private fun Throwable.convertToViewState() =
        when (this){
            is IOException -> ConfirmCreatingViewState.Error.NetworkError
            is NetworkErrorException -> {
                ConfirmCreatingViewState.Error.LimitError
            }
            else  -> ConfirmCreatingViewState.Error.UnexpectedError
        }
}
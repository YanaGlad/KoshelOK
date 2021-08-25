package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_STR
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ConfirmCreatingViewModel @Inject constructor(
    val repository: TransactionRepository
) : ViewModel() {
    companion object {
        val TRANSACTION_ID = 1L
    }

    private val _viewState: MutableLiveData<ConfirmCreatingViewState> = MutableLiveData()
    val viewState: LiveData<ConfirmCreatingViewState>
        get() = _viewState

    fun addTransaction(context: Context, transaction : WalletTransactionSample) {
        repository.addTransaction(
            context = context,
            item = WalletTransactionModel(
                id = TRANSACTION_ID,
                date = transaction.date,
                walletId = transaction.walletId,
                isIncome = transaction.isIncome,
                amount = transaction.amount,
                currency = Currency( transaction.currency.code, UNDEFINED_STR),
                transactionCategoryData = transaction.transactionCategoryData
            )
        )
            .doOnComplete {
                _viewState.postValue(ConfirmCreatingViewState.SuccessCreating)
            }
            .doOnError{
                _viewState.postValue(ConfirmCreatingViewState.Error.UnexpectedError)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}
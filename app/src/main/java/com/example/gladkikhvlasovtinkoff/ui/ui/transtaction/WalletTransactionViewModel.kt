package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.extension.toDelegateItemListWithDate
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WalletTransactionViewModel @Inject constructor(val repository: TransactionRepository) :
    ViewModel() {

    private val _viewState: MutableLiveData<TransactionListViewState> = MutableLiveData()
    val viewState: LiveData<TransactionListViewState>
        get() = _viewState

    fun getTransactionListByWalletId(walletId : Long) {
        repository.getAllTransactionsByWalletId(walletId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map{
                TransactionListViewState.Loaded(it.toDelegateItemListWithDate())
            }
            .doOnNext { viewState ->
                _viewState.postValue(viewState)

            }
            .doOnError {
                _viewState.postValue(TransactionListViewState.Error.UnexpectedError)
            }

            .observeOn(Schedulers.io())
            .subscribe()
    }
}
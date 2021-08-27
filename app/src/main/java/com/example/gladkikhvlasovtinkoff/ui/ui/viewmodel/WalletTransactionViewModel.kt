package com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.extension.toDelegateItemListWithDate
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.BalanceInfoViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.TransactionListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WalletTransactionViewModel @Inject constructor(val repository: TransactionRepository) :
    ViewModel() {

    init {

    }

    private val _viewState: MutableLiveData<TransactionListViewState> = MutableLiveData()
    val viewState: LiveData<TransactionListViewState>
        get() = _viewState

    private val _balanceInfoViewState : MutableLiveData<BalanceInfoViewState> = MutableLiveData()
    val balanceInfoViesState
        get() = _balanceInfoViewState

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

    fun deleteTransaction(walletTransactionModel: WalletTransactionModel){
        repository.deleteTransaction(walletTransactionModel)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { isDeleted ->

                },
                { exception ->

                }
            )
    }

    fun updateTransaction(walletTransactionModel: WalletTransactionModel){
        repository
            .updateTransaction(walletTransactionModel)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { model ->

                },
                { exception ->

                }
            )
    }
    fun loadBalanceInfo(walletId : Long){
        repository.getBalanceInfo(walletId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    _balanceInfoViewState.postValue(BalanceInfoViewState.Loaded(it))
                },
                {
                    it.printStackTrace()
                }
            )
    }
}
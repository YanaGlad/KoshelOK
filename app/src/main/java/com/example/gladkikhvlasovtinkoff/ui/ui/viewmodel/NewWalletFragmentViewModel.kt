package com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewWalletFragmentViewModel @Inject constructor(val repository: WalletRepository) :
    ViewModel() {

    private val disposeBag = CompositeDisposable()
    private val _viewState: MutableLiveData<NewWalletViewState> = MutableLiveData()
    val viewState: LiveData<NewWalletViewState>
        get() = _viewState

    fun updateWallet(walletData: WalletDataSample) {
        _viewState.value = NewWalletViewState.Loading
        disposeBag.add(
            repository.updateWallet(walletData.createWalletDataModel(), walletData.id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    {
                        _viewState.postValue(NewWalletViewState.SuccessOperation)
                    },
                    {
                        _viewState.postValue(it.convertToViewState())
                    }
                )
        )
    }

    fun addWallet(walletData: WalletDataSample) {
        _viewState.value = NewWalletViewState.Loading
        disposeBag.add(
            repository.addWallet(
                WalletData(
                    id = walletData.id,
                    username = "",
                    name = walletData.name,
                    limit = walletData.limit,
                    amount = walletData.amount,
                    currency = walletData.currency,
                    hidden = walletData.hidden
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    {
                        _viewState.postValue(NewWalletViewState.SuccessOperation)
                    },
                    {
                        it.convertToViewState()
                    }
                )
        )
    }

    private fun Throwable.convertToViewState() =
        when (this) {
            is IOException -> NewWalletViewState.Error.NetworkError
            else -> NewWalletViewState.Error.UnexpectedError
        }
}
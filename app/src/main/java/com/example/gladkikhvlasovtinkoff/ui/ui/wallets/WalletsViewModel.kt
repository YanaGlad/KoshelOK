package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



@HiltViewModel
class WalletsViewModel @Inject constructor(val repository: WalletRepository): ViewModel() {
    companion object {
        const val TEMP_USER_ID = 1L
        var TEMP_WALLET_ID = 1
    }
    init{
        getWalletList()
    }

    private val _viewState: MutableLiveData<WalletListViewState> = MutableLiveData()
    val viewState: LiveData<WalletListViewState>
        get() = _viewState

    fun deleteWallet(wallet : WalletData ){
        repository.deleteWaller(wallet )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnComplete {
                _viewState.postValue(WalletListViewState.SuccessOperation)
            }
            .doOnError {
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }

    fun addWallet(walletData: WalletDataSample) {
        repository.addWallet(
            WalletData(
                id = TEMP_WALLET_ID.toLong(),
                userId =TEMP_USER_ID,
                name = walletData.name,
                limit = walletData.limit,
                amount = walletData.amount,
                currency = walletData.currency
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnComplete {
                _viewState.postValue(WalletListViewState.SuccessOperation)
            }
            .doOnError {
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }

    fun getWalletList() {
        repository.getWalletsByUserId(TEMP_USER_ID)
            .doOnNext { viewState ->
                val list = (viewState as? WalletListViewState.Loaded)?.list
//                TEMP_WALLET_ID =  list?.last()?.id?.plus(1)?.toInt() ?: 1
                _viewState.postValue(viewState)
            }
            .doOnError {
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}
package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WalletsViewModel @Inject constructor(val repository: WalletRepository) : ViewModel() {
    companion object {
        const val TEMP_USERNAME = "test-tincoffJJ"
        var TEMP_WALLET_ID = 1
    }

    init {
        getWalletList()
//        addUser()
    }

    private val _viewState: MutableLiveData<WalletListViewState> = MutableLiveData()
    val viewState: LiveData<WalletListViewState>
        get() = _viewState

    private fun addUser() {
        repository.addUser(
            userRequest = UserRequest(
                name = "testoviy chelik",
                username = TEMP_USERNAME
            )
        )
            .doOnError {
                it.printStackTrace()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun addWallet(walletData: WalletDataSample) {
        repository.addWallet(
            WalletData(
                id = TEMP_WALLET_ID.toLong(),
                username = TEMP_USERNAME,
                name = walletData.name,
                limit = walletData.limit,
                amount = walletData.amount,
                currency = walletData.currency,
                hidden = walletData.hidden
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

    fun updateWallet(walletData: WalletDataSample){
        repository.updateWallet(walletData.createWalletDataModel())
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
        repository.getWalletsByUsername("testoviy chelik")
            .doOnNext { viewState ->
                if (viewState != null) {
                    _viewState.postValue(viewState)
                }
            }
            .doOnError {
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteWallet(wallet: WalletData) {
        repository.deleteWallet(wallet)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnComplete {
                _viewState.postValue(WalletListViewState.SuccessOperation)
            }
            .doOnError {
                it.printStackTrace()
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }
}



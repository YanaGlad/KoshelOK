package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.flowable.FlowableObserveOn
import io.reactivex.internal.operators.flowable.FlowableSubscribeOn
import io.reactivex.observers.DisposableSingleObserver
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

    private val disposeBag = CompositeDisposable()
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
        val disposable = repository.addWallet(
            WalletData(
                id = walletData.id,
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
            .subscribe(
                {
                    _viewState.postValue(WalletListViewState.SuccessOperation)
                },
                {}
            )
    }

    fun updateWallet(walletData: WalletDataSample) {
        val disposable = repository.updateWallet(walletData.createWalletDataModel())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    _viewState.postValue(WalletListViewState.SuccessOperation)
                },
                {}
            )
    }

    fun getWalletList() =
        repository.getWalletsByUsername("testoviy chelik")
            .doOnNext { viewState ->
                val list = (viewState as? WalletListViewState.Loaded)?.list
                TEMP_WALLET_ID = list?.last()?.id?.plus(1)?.toInt() ?: 1
                _viewState.postValue(viewState)
            }
            .doOnError {
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                _viewState.postValue(it)
            }

    fun deleteWallet(wallet: WalletData) =
        repository.deleteWallet(wallet)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    _viewState.postValue(WalletListViewState.SuccessOperation)
                },
                {
                    _viewState.postValue(WalletListViewState.Error.UnexpectedError)
                }
            )


    fun clear() {
        disposeBag.clear()
    }
}



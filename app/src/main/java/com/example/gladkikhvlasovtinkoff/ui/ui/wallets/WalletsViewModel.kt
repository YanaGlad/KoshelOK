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
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.flowable.FlowableObserveOn
import io.reactivex.internal.operators.flowable.FlowableSubscribeOn
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WalletsViewModel @Inject constructor(val repository: WalletRepository) : ViewModel() {
    init {
        getWalletList()
        loadWallets()
//        loadCourses(listOf())
    }
    private val disposables : MutableList<Disposable> = mutableListOf()

    private val disposeBag = CompositeDisposable()
    private val _viewState: MutableLiveData<WalletListViewState> = MutableLiveData()
    val viewState: LiveData<WalletListViewState>
        get() = _viewState

    private val _coursesViewState: MutableLiveData<CoursesPlateViewState> = MutableLiveData()
    val coursesViewState: LiveData<CoursesPlateViewState>
        get() = _coursesViewState

    private fun loadCourses(codes : List<String>) {
        _coursesViewState.value = CoursesPlateViewState.Loading
        val disposable = repository.getCurrenciesCourse(codes)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { courses ->
                    _coursesViewState.postValue(CoursesPlateViewState.Loaded(courses))
                },
                {
                    _coursesViewState.value = CoursesPlateViewState.Error
                }
            )
    }
    fun addWallet(walletData: WalletDataSample) {
        _viewState.value = WalletListViewState.Loading
        val disposable = repository.addWallet(
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
                    _viewState.postValue(WalletListViewState.SuccessOperation)
                },
                {
                    it.printStackTrace()
                }
            )
    }

    fun loadWallets(){
        // TODO обработка disposable
        val disposable = repository.loadWallets()
            .subscribeOn(Schedulers.io())
                // TODO достаточно одного subscribeOn в этом случае
            .observeOn(Schedulers.io())
            .subscribe(
                {viewState ->
                    _viewState.postValue(viewState)
                },
                {
                    it.printStackTrace()
                    // TODO вывод ошибок
                }
            )
    }

    fun updateWallet(walletData: WalletDataSample) {
        _viewState.value = WalletListViewState.Loading
        val disposable = repository.updateWallet(walletData.createWalletDataModel(), walletData.id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    _viewState.postValue(WalletListViewState.SuccessOperation)
                },
                {
                    it.printStackTrace()
                    // TODO вывод ошибок
                }
            )
        disposables.add(disposable)
    }

   fun getWalletList() {
        val disposable = repository.getWallets()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                _viewState.postValue(it)
            }
    }

    fun getAllWalletsBalance(currencyCharCode: String,
                             username: String)  {
        _viewState.value = WalletListViewState.Loading
        repository.getAllWalletsBalance(currencyCharCode)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    _viewState.postValue(it)
                    _viewState.postValue(WalletListViewState.SuccessOperation)
                },
                {
                    _viewState.postValue(WalletListViewState.Error.UnexpectedError)
                }
            )
    }

    fun deleteWallet(wallet: WalletData) {
        _viewState.value = WalletListViewState.Loading
        val disposable = repository.deleteWallet(wallet)
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
    }


    fun clear() {
        for(item in disposables){
            item.dispose()
        }
    }
}



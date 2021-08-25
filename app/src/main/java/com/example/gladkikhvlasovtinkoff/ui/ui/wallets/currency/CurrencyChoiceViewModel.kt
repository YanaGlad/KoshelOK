package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class CurrencyChoiceViewModel
@Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    private var _viewState: MutableLiveData<CurrencyListViewState> = MutableLiveData()
    val viewState: LiveData<CurrencyListViewState>
        get() = _viewState

    init{
        getCurrencies()
        loadCurrencies()
    }

    private fun loadCurrencies(){
        repository.loadCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnError{
                it.printStackTrace()
            }
            .subscribe()
    }

    fun getCurrencies(){
        repository.getCurrencies()
            .doOnNext{ viewState ->
                _viewState.postValue(viewState)
            }
            .doOnError{
                _viewState.postValue(CurrencyListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }

}
package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
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

    init {
        val currencyList = mutableListOf<Currency>()
        currencyList.add(Currency( "DOL", "Dollar"))
        currencyList.add(Currency("RUB", "Ruble"))
        currencyList.add(Currency( "EUR", "Euro"))
        addCurrencies(currencyList)
        getCurrencies()
    }

    fun addCurrencies(currencies : List<Currency>){
        repository.insertCurrencies(currencies)
            .doOnError{
                _viewState.postValue(CurrencyListViewState.Error.UnexpectedError)
            }
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
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
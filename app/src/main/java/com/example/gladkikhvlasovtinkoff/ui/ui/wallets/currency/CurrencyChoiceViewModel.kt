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
        currencyList.add(Currency(1, "00299", "Dollar"))
        currencyList.add(Currency(2, "12314", "Ruble"))
        currencyList.add(Currency(3, "423525", "Euro"))
        currencyList.add(Currency(4, "00299", "Dollar"))
        currencyList.add(Currency(5, "12314", "Ruble"))
        currencyList.add(Currency(6, "423525", "Euro"))
        currencyList.add(Currency(7, "00299", "Dollar"))
        currencyList.add(Currency(8, "12314", "Ruble"))
        currencyList.add(Currency(9, "423525", "Euro"))
        currencyList.add(Currency(10, "00299", "Dollar"))
        currencyList.add(Currency(11, "12314", "Ruble"))
        currencyList.add(Currency(12, "423525", "Euro"))
        currencyList.add(Currency(13, "00299", "Dollar"))
        currencyList.add(Currency(14, "12314", "Ruble"))
        currencyList.add(Currency(15, "423525", "Euro"))
        currencyList.add(Currency(16, "00299", "Dollar"))
        currencyList.add(Currency(17, "12314", "Ruble"))
        currencyList.add(Currency(18, "423525", "Euro"))
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
package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.CurrencyDataI
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample

class CurrencyChoiceViewModel : ViewModel() {
    var currencyList : MutableLiveData<ArrayList<CurrencyDataI>> = MutableLiveData<ArrayList<CurrencyDataI>>()
    val dataSample: MutableLiveData<WalletDataSample> = MutableLiveData<WalletDataSample>()

    init {
        dataSample.value = WalletDataSample()
        currencyList.value = ArrayList()
        currencyList.value?.add(CurrencyDataI("00299", "Dollar", false))
        currencyList.value?.add(CurrencyDataI("12314", "Ruble", false))
        currencyList.value?.add(CurrencyDataI("423525", "Euro", false))
        currencyList.value?.add(CurrencyDataI("00299", "Dollar", false))
        currencyList.value?.add(CurrencyDataI("12314", "Ruble", false))
        currencyList.value?.add(CurrencyDataI("423525", "Euro", false))
        currencyList.value?.add(CurrencyDataI("00299", "Dollar", false))
        currencyList.value?.add(CurrencyDataI("12314", "Ruble", false))
        currencyList.value?.add(CurrencyDataI("423525", "Euro", false))
        currencyList.value?.add(CurrencyDataI("00299", "Dollar", false))
        currencyList.value?.add(CurrencyDataI("12314", "Ruble", false))
        currencyList.value?.add(CurrencyDataI("423525", "Euro", false))
        currencyList.value?.add(CurrencyDataI("00299", "Dollar", false))
        currencyList.value?.add(CurrencyDataI("12314", "Ruble", false))
        currencyList.value?.add(CurrencyDataI("423525", "Euro", false))
        currencyList.value?.add(CurrencyDataI("00299", "Dollar", false))
        currencyList.value?.add(CurrencyDataI("12314", "Ruble", false))
        currencyList.value?.add(CurrencyDataI("423525", "Euro", false))

    }

}
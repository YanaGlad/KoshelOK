package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.CurrencyData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample

class CurrencyChoiceViewModel : ViewModel() {
    var currencyList : MutableLiveData<ArrayList<CurrencyData>> = MutableLiveData<ArrayList<CurrencyData>>()
    val dataSample: MutableLiveData<WalletDataSample> = MutableLiveData<WalletDataSample>()

    init {
        dataSample.value = WalletDataSample()
        currencyList.value = ArrayList()
        currencyList.value?.add(CurrencyData(1, "dol", "Dollar", false))
        currencyList.value?.add(CurrencyData(2, "dol", "Ruble", false))
        currencyList.value?.add(CurrencyData(3, "dol", "Euro", false))
        currencyList.value?.add(CurrencyData(4, "dol", "Dollar", false))
        currencyList.value?.add(CurrencyData(5, "dol", "Ruble", false))
        currencyList.value?.add(CurrencyData(6, "dol", "Euro", false))
        currencyList.value?.add(CurrencyData(7, "dol", "Dollar", false))
        currencyList.value?.add(CurrencyData(8, "dol", "Ruble", false))
        currencyList.value?.add(CurrencyData(9, "dol", "Euro", false))
        currencyList.value?.add(CurrencyData(10, "dol", "Dollar", false))
        currencyList.value?.add(CurrencyData(11, "dol", "Ruble", false))
        currencyList.value?.add(CurrencyData(12, "dol", "Euro", false))
        currencyList.value?.add(CurrencyData(13, "dol", "Dollar", false))
        currencyList.value?.add(CurrencyData(14, "dol", "Ruble", false))
        currencyList.value?.add(CurrencyData(15, "dol", "Euro", false))
        currencyList.value?.add(CurrencyData(16, "dol", "Dollar", false))
        currencyList.value?.add(CurrencyData(17, "dol", "Ruble", false))
        currencyList.value?.add(CurrencyData(18, "dol", "Euro", false))

    }

}
package com.example.gladkikhvlasovtinkoff.db.dataprovider

import com.example.gladkikhvlasovtinkoff.model.Currency
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalCurrencyDataProvider {
    fun getCurrencies() : Flowable<List<Currency>>
    fun getCurrencyByCode(code : String) : Single<Currency>
    fun insertCurrencies(currencies : List<Currency>)
}

package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.model.Currency
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalCurrencyDataProvider {
    fun getCurrencies() : Flowable<List<Currency>>
    fun getCurrencyByCode(code : String) : Single<Currency>
    fun insertCurrencies(currencies : List<Currency>) : Completable
}
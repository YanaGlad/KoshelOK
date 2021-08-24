package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency.CurrencyListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CurrencyRepository {
    fun getCurrencies() : Flowable<CurrencyListViewState>
    fun loadCurrencies() : Completable
}
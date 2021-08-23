package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.LocalCurrencyDataProvider
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency.CurrencyListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val localCurrencyDataProvider: LocalCurrencyDataProvider
) : CurrencyRepository {

    override fun getCurrencies(): Flowable<CurrencyListViewState> =
        localCurrencyDataProvider.getCurrencies()
            .map {  list ->
                CurrencyListViewState.Loaded(list)
            }

    override fun insertCurrencies(currencies: List<Currency>): Completable =
        localCurrencyDataProvider.insertCurrencies(currencies)

}
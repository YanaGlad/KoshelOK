package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.LocalCurrencyDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency.CurrencyListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val localCurrencyDataProvider: LocalCurrencyDataProvider,
    private val remoteWalletDataProvider: RemoteWalletDataProvider
    ) : CurrencyRepository {

    override fun getCurrencies(): Flowable<CurrencyListViewState> =
        localCurrencyDataProvider.getCurrencies()
            .map {  list ->
                CurrencyListViewState.Loaded(list)
            }

    override fun loadCurrencies(): Completable =
        remoteWalletDataProvider
            .getAllCurrencies()
            .flatMapCompletable { currencies ->
                Completable.create{ emitter ->
                    try{
                        localCurrencyDataProvider.insertCurrencies(currencies)
                        emitter.onComplete()
                    }
                    catch (e : Exception){
                        emitter.onError(e)
                    }
                }
            }



}
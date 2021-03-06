package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.dataprovider.LocalCurrencyDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency.CurrencyListViewState
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val localCurrencyDataProvider: LocalCurrencyDataProvider,
    private val remoteWalletDataProvider: RemoteWalletDataProvider
) : CurrencyRepository {

    private val compositeDisposable = CompositeDisposable()

    override fun getCurrencies(): Flowable<CurrencyListViewState> =
        localCurrencyDataProvider.getCurrencies()
            .map { list ->
                CurrencyListViewState.Loaded(list)
            }

    override fun loadCurrencies(): Single<CurrencyListViewState> =
        Single.create { emitter ->
            compositeDisposable.add(remoteWalletDataProvider
                .getAllCurrencies()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { currencies ->
                        localCurrencyDataProvider.insertCurrencies(currencies)
                        emitter.onSuccess(CurrencyListViewState.Loaded(currencies))
                    },
                    {
                        when (it) {
                            is IOException -> emitter.onSuccess(CurrencyListViewState.Error.NetworkError)
                            else -> emitter.onSuccess(CurrencyListViewState.Error.UnexpectedError)
                        }
                    }
                )
            )
        }
}

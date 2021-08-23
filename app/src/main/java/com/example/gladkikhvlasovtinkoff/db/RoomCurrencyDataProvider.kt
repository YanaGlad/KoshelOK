package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import com.example.gladkikhvlasovtinkoff.model.Currency
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomCurrencyDataProvider @Inject constructor(val dao: CurrencyDao) :
    LocalCurrencyDataProvider {
    override fun getCurrencies(): Flowable<List<Currency>> =
        dao.getCurrencies()
            .map { currencies ->
                currencies.map { currency ->
                    Currency(
                        id = currency.id,
                        code = currency.code,
                        name = currency.name
                    )
                }
            }

    override fun getCurrencyById(id: Long): Single<Currency> =
        dao.getCurrencyById(id)
            .map { currency ->
                Currency(
                    id = currency.id,
                    code = currency.code,
                    name = currency.name
                )
            }

    override fun insertCurrencies(currencies: List<Currency>) =
        Completable.create { emitter ->
            dao.insertCurrencies(currencies =
            currencies.map { currency ->
                CurrencyDB(
                    id = currency.id,
                    code = currency.code,
                    name = currency.name
                )
            }
            )
            emitter.onComplete()
        }

}
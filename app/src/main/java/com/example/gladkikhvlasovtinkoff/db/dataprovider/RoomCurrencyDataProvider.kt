package com.example.gladkikhvlasovtinkoff.db.dataprovider

import com.example.gladkikhvlasovtinkoff.db.dao.CurrencyDao
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import com.example.gladkikhvlasovtinkoff.model.Currency
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
                        code = currency.code,
                        name = currency.name
                    )
                }
            }

    override fun getCurrencyByCode(code: String): Single<Currency> =
        dao.getCurrencyByCode(code)
            .map { currency ->
                Currency(
                    code = currency.code,
                    name = currency.name
                )
            }

    override fun insertCurrencies(currencies: List<Currency>) =
        dao.insertCurrencies(currencies =
        currencies.map { currency ->
            CurrencyDB(
                code = currency.code,
                name = currency.name
            )
        }
        )
}

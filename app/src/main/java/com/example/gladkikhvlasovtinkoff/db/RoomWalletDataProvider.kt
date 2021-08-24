package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import com.example.gladkikhvlasovtinkoff.db.entity.WalletDB
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class RoomWalletDataProvider @Inject constructor(private val dao: WalletDao) :
    LocalWalletDataProvider {

    override fun getWalletsByUserId(userId : Long): Flowable<List<WalletData>> =
        dao.getWalletsByUserId(userId)
            .map { wallets ->
                wallets
                    .map { wallet ->
                        WalletData(
                            id = wallet.id,
                            userId = wallet.userId,
                            name = wallet.name,
                            limit = wallet.limit,
                            amount = wallet.amount,
                            currency = Currency(
                                id = wallet.currency.id,
                                code = wallet.currency.code,
                                name = wallet.currency.name
                            )
                        )
                    }
            }


    override fun getWalletById(id: Long): Single<WalletData> =
        dao.getWalletById(id)
            .map { wallet ->
                WalletData(
                    id = wallet.id,
                    userId = wallet.userId,
                    name = wallet.name,
                    limit = wallet.limit,
                    amount = wallet.amount,
                    currency = Currency(
                        id = wallet.currency.id,
                        code = wallet.currency.code,
                        name = wallet.currency.name
                    )
                )
            }

    //TODO - подумать насчет обработки ошибки и стоит ли их вообще делать
    //TODO - заменить повторяющийся код

    override fun insertWallet(wallet: WalletData): Completable =
        Completable.create { emitter ->
            try {
                dao.insertWallet(
                    WalletDB(
                        id = wallet.id,
                        userId = wallet.userId,
                        name = wallet.name,
                        limit = wallet.limit,
                        amount = wallet.amount,
                        currency = CurrencyDB(
                            id = wallet.currency.id,
                            code = wallet.currency.code,
                            name = wallet.currency.name
                        )
                    )
                )
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    override fun deleteWallet(wallet: WalletData): Completable =
        Completable.create { emitter ->
            try {
                dao.deleteWallet(
                    WalletDB(
                        id = wallet.id,
                        userId = wallet.userId,
                        name = wallet.name,
                        limit = wallet.limit,
                        amount = wallet.amount,
                        currency = CurrencyDB(
                            id = wallet.currency.id,
                            code = wallet.currency.code,
                            name = wallet.currency.name
                        )
                    )
                )
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    override fun updateWallet(wallet: WalletData): Completable =
        Completable.create { emitter ->
            try {
                dao.updateWallet(
                    WalletDB(
                        id = wallet.id,
                        userId = wallet.userId,
                        name = wallet.name,
                        limit = wallet.limit,
                        amount = wallet.amount,
                        currency = CurrencyDB(
                            id = wallet.currency.id,
                            code = wallet.currency.code,
                            name = wallet.currency.name
                        )
                    )
                )
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

}
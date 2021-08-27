package com.example.gladkikhvlasovtinkoff.db.dataprovider

import com.example.gladkikhvlasovtinkoff.db.dao.WalletDao
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import com.example.gladkikhvlasovtinkoff.db.entity.WalletDB
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomWalletDataProvider @Inject constructor(private val dao: WalletDao) :
    LocalWalletDataProvider {

    override fun getWalletsByUsername(username : String): Flowable<List<WalletData>> =
        dao.getWalletsByUsername(username)
            .map { wallets ->
                wallets
                    .map { wallet ->
                        WalletData(
                            id = wallet.id,
                            username = wallet.username,
                            name = wallet.name,
                            limit = wallet.limit,
                            amount = wallet.amount,
                            currency = Currency(
                                code = wallet.currency.code,
                                name = wallet.currency.name
                            ),
                            hidden = wallet.hidden
                        )
                    }
            }


    override fun getWalletById(id: Long): Single<WalletData> =
        dao.getWalletById(id)
            .map { wallet ->
                WalletData(
                    id = wallet.id,
                    username = wallet.username,
                    name = wallet.name,
                    limit = wallet.limit,
                    amount = wallet.amount,
                    currency = Currency(
                        code = wallet.currency.code,
                        name = wallet.currency.name
                    ),
                    hidden = wallet.hidden
                )
            }

    //TODO - подумать насчет обработки ошибки и стоит ли их вообще делать
    //TODO - заменить повторяющийся код

    override fun insertWallet(wallet: WalletData) =
                dao.insertWallet(
                    WalletDB(
                        id = wallet.id,
                        username = wallet.username,
                        name = wallet.name,
                        limit = wallet.limit,
                        amount = wallet.amount,
                        currency = CurrencyDB(
                            code = wallet.currency.code,
                            name = wallet.currency.name
                        ),
                        hidden = wallet.hidden
                    )
                )

    override fun insertWallets(wallets: List<WalletData>) =
        dao.insertWallets(
            wallets.map{ wallet ->
                WalletDB(
                    id = wallet.id,
                    username = wallet.username,
                    name = wallet.name,
                    limit = wallet.limit,
                    amount = wallet.amount,
                    currency = CurrencyDB(
                        code = wallet.currency.code,
                        name = wallet.currency.name
                    ),
                    hidden = wallet.hidden
                )
            }
        )


    override fun deleteWallet(wallet: WalletData) =
                dao.deleteWallet(
                    WalletDB(
                        id = wallet.id,
                        username = wallet.username,
                        name = wallet.name,
                        limit = wallet.limit,
                        amount = wallet.amount,
                        currency = CurrencyDB(
                            code = wallet.currency.code,
                            name = wallet.currency.name
                        ),
                        hidden = wallet.hidden
                    )
                )



    override fun updateWallet(wallet: WalletData) =
                dao.updateWallet(
                    WalletDB(
                        id = wallet.id,
                        username = wallet.username,
                        name = wallet.name,
                        limit = wallet.limit,
                        amount = wallet.amount,
                        currency = CurrencyDB(
                            code = wallet.currency.code,
                            name = wallet.currency.name
                        ),
                        hidden = wallet.hidden
                    )
                )
}
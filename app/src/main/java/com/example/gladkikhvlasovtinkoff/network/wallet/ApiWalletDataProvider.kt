package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Single
import javax.inject.Inject

class ApiWalletDataProvider @Inject constructor(private val api : TransactionApi)
    : RemoteWalletDataProvider {

//    override fun findWalletById(walletId: Long): Single<WalletData> =
//        api.findWalletById(walletId)
//            .map{ response ->
//                WalletData(
//                    id = response.id,
//                    userId = response.,
//                    name = response.name,
//                    limit = response.limit,
//                    amount = response.balance,
//                    currency = Currency(
//                        id = response.currency.id,
//                        code = response.currency.code,
//                        name = response.currency.name
//                    )
//                )
//            }
//
//    override fun getAllWalletByUserId(userId: Long): Single<List<WalletData>> =
//        api.getAllWalletsByUserId(userId)
//            .map{ list ->
//                list.map{ wallet ->
//                    WalletData(
//                        id = wallet.id,
//                        userId = wallet.userId,
//                        name = wallet.name,
//                        limit = wallet.limit,
//                        amount = wallet.balance,
//                        currency = Currency(
//                            id = wallet.currency.id,
//                            code = wallet.currency.code,
//                            name = wallet.currency.name
//                        )
//                    )
//                }
//            }
}

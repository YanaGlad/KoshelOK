package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.LocalWalletDataProvider
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.CurrencyCourse
import com.example.gladkikhvlasovtinkoff.model.UserBalanceInfo
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletCreateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletUpdateRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

// TODO SOLID
class WalletRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localWalletDataProvider: LocalWalletDataProvider,
    private val authDataHolder: AuthDataHolder
) : WalletRepository {

    override fun addWallet(wallet: WalletData): Single<WalletListViewState> =

        Single.create { emitter ->
            // TODO вот это все должно быть в сущности WebApi, которая занимается
            // перевести в обычную цепочку rx
            // можно использовать flatmap в нем maybe и обрабатывать пустое состояние
            // можно просто кинуть UnauthorizedException и обработать в onError
            // можно сделать объект обертку и складывать в него состояние и обрабатывать в onNext
            if (authDataHolder.isAuth()) {
                val request = WalletCreateRequest(
                    currencyCharCode = wallet.currency.code,
                    limit = wallet.limit,
                    name = wallet.name,
                    username = authDataHolder.getUserKey()
                )
                remoteWalletDataProvider.createWallet(
                    request
                ).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(
                        { wallet ->
                            localWalletDataProvider
                                .insertWallet(
                                    WalletData(
                                        id = wallet.id,
                                        username = authDataHolder.getUserKey(),
                                        name = wallet.name,
                                        limit = wallet.limit,
                                        amount = wallet.amount,
                                        currency = Currency(
                                            code = wallet.currency.code,
                                            name = wallet.currency.name
                                        ),
                                        hidden = wallet.hidden
                                    )
                                )
                            emitter.onSuccess(WalletListViewState.SuccessOperation)
                        },
                        { throwable ->
                            emitter.onSuccess(throwable.convertToViewState())
                        }
                    )
            } else
                emitter.onSuccess(WalletListViewState.Error.AuthError)
        }

    override fun deleteWallet(wallet: WalletData): Single<WalletListViewState> =
        Single.create { emitter ->
            remoteWalletDataProvider
                .deleteWallet(wallet.id)
                .subscribe(
                    { isDeleted ->
                        if (isDeleted) {
                            localWalletDataProvider
                                .deleteWallet(wallet)
                        }
                        emitter.onSuccess(WalletListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }


    override fun getWallets(): Flowable<WalletListViewState> =
        if (authDataHolder.isAuth())
            localWalletDataProvider
                .getWalletsByUsername(authDataHolder.getUserKey())
                .map {
                    WalletListViewState.Loaded(it)
                }
        else
            Flowable.just(WalletListViewState.Error.AuthError)

    override fun loadWallets(): Single<WalletListViewState> =
        Single.create { emitter ->
            if (authDataHolder.isAuth())
                remoteWalletDataProvider.getAllWalletByUsername(
                    authDataHolder.getUserKey()
                )
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { wallets ->
                            localWalletDataProvider.insertWallets(
                                wallets.map { wallet ->
                                    WalletData(
                                        id = wallet.id,
                                        username = authDataHolder.getUserKey(),
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
                            )
                            emitter.onSuccess(WalletListViewState.SuccessOperation)

                        },
                        { throwable ->
                            emitter.onSuccess(throwable.convertToViewState())
                        }
                    )
            else
                emitter.onSuccess(WalletListViewState.Error.AuthError)
        }


    override fun updateWallet(wallet: WalletData, walletId: Long): Single<WalletListViewState> =
        Single.create { emitter ->
            remoteWalletDataProvider
                .updateWallet(
                    WalletUpdateRequest(
                        wallet.hidden,
                        wallet.limit,
                        wallet.name
                    ), wallet.id
                )
                .subscribe(
                    { wallet ->
                        localWalletDataProvider.updateWallet(wallet)
                        emitter.onSuccess(WalletListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }


    override fun getAllWalletsBalance(
        currencyCharCode: String
    ): Single<WalletListViewState> =
        Single.create { emitter ->
            remoteWalletDataProvider
                .getAllWalletsBalance(currencyCharCode, authDataHolder.getUserKey())
                .subscribe(
                    { balance ->
                        WalletListViewState.LoadedString(balance)
                        emitter.onSuccess(WalletListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }


    override fun getCurrenciesCourse(codes: List<String>) : Single<List<CurrencyCourse>> =
        if (codes.isNotEmpty()) {
            var toZipWith = remoteWalletDataProvider.getCurrencyCourse(codes[0])
                .map { course ->
                    listOf(course)
                }
            for (i in 1 until codes.size) {
                toZipWith = toZipWith.zipWith(
                    remoteWalletDataProvider.getCurrencyCourse(codes[i]),
                    { list , course -> list + course}
                )
            }
            toZipWith
        } else
            Single.just(listOf())

    override fun getBalanceInfo(): Single<UserBalanceInfo> =
        remoteWalletDataProvider
            .getAllWalletByUsername(authDataHolder.getUserKey())
            .flatMap{ wallets ->
                remoteWalletDataProvider.getAllExpenses(wallets)
                    .zipWith(
                        remoteWalletDataProvider.getAllIncome(wallets),
                        { expenses, income ->
                            UserBalanceInfo(
                                expenses = expenses,
                                income = income
                            )
                        }
                    )
            }



    private fun Throwable.convertToViewState(): WalletListViewState =
        when (this) {
            is IOException -> WalletListViewState.Error.NetworkError
            else -> WalletListViewState.Error.UnexpectedError
        }


}

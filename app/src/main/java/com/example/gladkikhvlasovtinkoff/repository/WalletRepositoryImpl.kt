package com.example.gladkikhvlasovtinkoff.repository

import android.accounts.NetworkErrorException
import com.example.gladkikhvlasovtinkoff.db.LocalWalletDataProvider
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.UserCateroryKeys
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletCreateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localWalletDataProvider: LocalWalletDataProvider
) : WalletRepository {

    //TODO = вынести логику в другое место
    override fun addUser(userRequest: UserRequest): Single<UserResponse> =
        remoteWalletDataProvider.addUser(userRequest)


    override fun addWallet(wallet: WalletData): Single<WalletListViewState> =
        Single.create { emitter ->
            remoteWalletDataProvider.createWallet(
                WalletCreateRequest(
                    currencyCharCode = wallet.currency.code,
                    limit = wallet.limit,
                    name = wallet.name,
                    username = wallet.username
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    { wallet ->
                        localWalletDataProvider
                            .insertWallet(wallet)
                        emitter.onSuccess(WalletListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }

    override fun deleteWallet(wallet: WalletData) : Single<WalletListViewState> =
        Single.create{ emitter ->
            remoteWalletDataProvider
                .deleteWallet(wallet.id)
                .subscribe(
                    {wallet ->
                        localWalletDataProvider
                            .deleteWallet(wallet)
                        emitter.onSuccess(WalletListViewState.SuccessOperation)
                    },
                    {throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }



    override fun getWalletsByUsername(username: String): Flowable<WalletListViewState> =
        localWalletDataProvider
            .getWalletsByUsername(username)
            .map {
                WalletListViewState.Loaded(it)
            }

    override fun updateWallet(wallet: WalletData): Completable =
        Completable.create{ emitter ->
            localWalletDataProvider.updateWallet(wallet)
            emitter.onComplete()
        }

    private fun Throwable.convertToViewState() : WalletListViewState =
        when(this){
            is IOException -> WalletListViewState.Error.NetworkError
            else -> WalletListViewState.Error.UnexpectedError
        }


}
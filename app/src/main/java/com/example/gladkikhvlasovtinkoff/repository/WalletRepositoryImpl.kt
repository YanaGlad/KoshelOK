package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.LocalWalletDataProvider
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.request.WalletCreateRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localWalletDataProvider: LocalWalletDataProvider
) : WalletRepository {

    //TODO = вынести логику в другое место
    override fun addUser(userRequest: UserRequest): Single<UserResponse> =
        remoteWalletDataProvider.addUser(userRequest)


    override fun addWallet(wallet: WalletData): Completable =
        remoteWalletDataProvider.createWallet(
            WalletCreateRequest(
                currencyCharCode = wallet.currency.code,
                limit = wallet.limit,
                name = wallet.name,
                username = wallet.username
            )
        ).flatMapCompletable { walletData ->
            Completable.create { emitter ->
                try {
                    localWalletDataProvider
                        .insertWallet(walletData)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }


    override fun deleteWallet(wallet: WalletData): Completable =
        remoteWalletDataProvider.deleteWallet(wallet.id)
            .flatMapCompletable { walletData ->
                Completable.create { emitter ->
                    try {
                        localWalletDataProvider
                            .deleteWallet(walletData)
                        emitter.onComplete()
                    } catch (e: Exception) {
                        emitter.onError(e)
                    }
                }
            }


    override fun getWalletsByUsername(username: String): Flowable<WalletListViewState> =
        localWalletDataProvider.getWalletsByUsername(username)
            .map {
                WalletListViewState.Loaded(it)
            }

    override fun updateWallet(wallet: WalletData): Completable =
        Completable.create {
            localWalletDataProvider.updateWallet(wallet)
        }

}
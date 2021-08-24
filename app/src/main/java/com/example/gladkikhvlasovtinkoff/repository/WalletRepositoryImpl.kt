package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.LocalWalletDataProvider
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localWalletDataProvider : LocalWalletDataProvider
) : WalletRepository{
    override fun addWallet(wallet: WalletData): Completable =
        localWalletDataProvider.insertWallet(wallet)

    override fun deleteWaller(wallet: WalletData): Completable =
        localWalletDataProvider.deleteWallet(wallet)

    override fun getWalletsByUserId(userId: Long): Flowable<WalletListViewState> =
        localWalletDataProvider.getWalletsByUserId(userId)
            .map{
                WalletListViewState.Loaded(it)
            }

    override fun updateWallet(wallet: WalletData): Completable =
        localWalletDataProvider.updateWallet(wallet)


}
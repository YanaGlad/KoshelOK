package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WalletRepository {
    fun addWallet(wallet : WalletData) : Single<WalletListViewState>
    fun deleteWallet(wallet : WalletData) : Single<WalletListViewState>
    fun getWallets() : Flowable<WalletListViewState>
    fun loadWallets() : Single<WalletListViewState>
    fun updateWallet(wallet: WalletData) : Completable
}
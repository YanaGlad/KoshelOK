package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WalletRepository {
    fun addWallet(wallet : WalletData) : Completable
    fun deleteWaller(wallet : WalletData) : Completable
    fun getWalletsByUserId(userId : Long) : Flowable<WalletListViewState>
    fun updateWallet(wallet: WalletData) : Completable
}
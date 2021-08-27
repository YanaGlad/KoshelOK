package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.CurrencyCourse
import com.example.gladkikhvlasovtinkoff.model.UserBalanceInfo
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Flowable
import io.reactivex.Single

interface WalletRepository {
    fun getAllWalletsBalance(currencyCharCode: String): Single<WalletListViewState>
    fun addWallet(wallet : WalletData) : Single<WalletListViewState>
    fun deleteWallet(wallet : WalletData) : Single<WalletListViewState>
    fun getWallets() : Flowable<WalletListViewState>
    fun loadWallets() : Single<WalletListViewState>
    fun updateWallet(wallet: WalletData, walletId : Long) : Single<WalletListViewState>
    fun getCurrenciesCourse(codes : List<String>) : Single<List<CurrencyCourse>>
    fun getBalanceInfo() : Single<UserBalanceInfo>
}
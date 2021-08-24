package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.request.UserRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WalletRepository {
    fun addUser(userRequest : UserRequest) : Single<UserResponse>
    fun addWallet(wallet : WalletData) : Completable
    fun deleteWaller(wallet : WalletData) : Completable
    fun getWalletsByUsername(username : String) : Flowable<WalletListViewState>
    fun updateWallet(wallet: WalletData) : Completable
}
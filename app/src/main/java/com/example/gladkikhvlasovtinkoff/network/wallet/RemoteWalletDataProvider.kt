package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Single

interface RemoteWalletDataProvider {

    fun findWalletById(walletId : Long) : Single<WalletData>

    fun getAllWalletByUserId(userId : Long) : Single<List<WalletData>>

}
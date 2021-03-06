package com.example.gladkikhvlasovtinkoff.db.dataprovider

import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalWalletDataProvider {
    fun getWalletsByUsername(username : String) : Flowable<List<WalletData>>
    fun getWalletById(id : Long) : Single<WalletData>
    fun insertWallet(wallet: WalletData)
    fun insertWallets(wallets: List<WalletData>)
    fun deleteWallet(wallet: WalletData)
    fun updateWallet(wallet: WalletData)
}

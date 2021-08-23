package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.WalletWithCurrency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalWalletDataProvider {
    fun getWalletsByUserId(userId : Long) : Flowable<List<WalletData>>
    fun getWalletById(id : Long) : Single<WalletData>
    fun insertWallet(wallet: WalletData) : Completable
    fun deleteWallet(wallet: WalletData) : Completable
    fun updateWallet(wallet: WalletData) : Completable
}

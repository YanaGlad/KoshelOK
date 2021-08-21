package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider
) : WalletRepository{

}
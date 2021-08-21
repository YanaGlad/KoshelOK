package com.example.gladkikhvlasovtinkoff.network.wallet

import javax.inject.Inject

class ApiWalletDataProvider @Inject constructor(val api : TransactionApi)
    : RemoteWalletDataProvider {
}
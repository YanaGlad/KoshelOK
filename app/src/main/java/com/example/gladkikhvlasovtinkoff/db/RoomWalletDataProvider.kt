package com.example.gladkikhvlasovtinkoff.db

import javax.inject.Inject

class RoomWalletDataProvider @Inject constructor(val dao : WalletDao) : LocalWalletDataProvider {
}
package com.example.gladkikhvlasovtinkoff.db

import javax.inject.Inject

class RoomTransactionDataProvider @Inject constructor(val dao : TransactionDao)
    : LocalTransactionDataProvider {
}
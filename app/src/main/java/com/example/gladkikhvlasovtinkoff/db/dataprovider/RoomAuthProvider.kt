package com.example.gladkikhvlasovtinkoff.db.dataprovider

import com.example.gladkikhvlasovtinkoff.db.WalletDatabase
import javax.inject.Inject


class RoomAuthProvider  @Inject constructor(val database : WalletDatabase): LocalAuthProvider {
    override fun clearAllTables() = database.clearAllTables()
}

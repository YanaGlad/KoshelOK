package com.example.gladkikhvlasovtinkoff.db

import javax.inject.Inject


class RoomAuthProvider  @Inject constructor(val database : WalletDatabase): LocalAuthProvider {
    override fun clearAllTables() = database.clearAllTables()
}

package com.example.gladkikhvlasovtinkoff.db

import androidx.room.Database

@Database(entities = [WalletDB::class, TransactionDB::class, CurrencyDB::class], version = 1,
    exportSchema = false)
abstract class WalletDatabase {
    abstract fun walletDao() : WalletDao
}
package com.example.gladkikhvlasovtinkoff.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WalletDB::class, TransactionDB::class, CurrencyDB::class], version = 1,
    exportSchema = false)
abstract class WalletDatabase : RoomDatabase(){
    abstract fun walletDao() : WalletDao
    abstract fun transactionDao() : TransactionDao
    abstract fun currencyDao() : CurrencyDao
}
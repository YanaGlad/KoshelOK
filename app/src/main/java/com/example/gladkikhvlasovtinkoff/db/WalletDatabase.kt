package com.example.gladkikhvlasovtinkoff.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gladkikhvlasovtinkoff.db.dao.CategoryDao
import com.example.gladkikhvlasovtinkoff.db.dao.CurrencyDao
import com.example.gladkikhvlasovtinkoff.db.dao.TransactionDao
import com.example.gladkikhvlasovtinkoff.db.dao.WalletDao
import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.db.entity.WalletDB

@Database(entities = [WalletDB::class, TransactionDB::class, CurrencyDB::class, CategoryDB::class], version = 1,
    exportSchema = false)
abstract class WalletDatabase : RoomDatabase(){
    abstract fun walletDao() : WalletDao
    abstract fun transactionDao() : TransactionDao
    abstract fun currencyDao() : CurrencyDao
    abstract fun categoryDao() : CategoryDao
}
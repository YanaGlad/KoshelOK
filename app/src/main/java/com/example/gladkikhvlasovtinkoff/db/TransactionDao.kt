package com.example.gladkikhvlasovtinkoff.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//@Dao
//interface TransactionDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllTransactions(data: List<WalletTransactionEntity>)
//
//    @Query("SELECT * FROM transaction_table")
//    fun getAllTransactions(): List<WalletTransactionEntity>
//}
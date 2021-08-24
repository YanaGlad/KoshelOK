package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionDB (
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val date : Long,
    val walletId : Long,
    val isIncome : Boolean,
    val amount : String,
    @Embedded
    val currency: CurrencyDB,
    @Embedded
    val transactionCategoryData: CategoryDB
    )
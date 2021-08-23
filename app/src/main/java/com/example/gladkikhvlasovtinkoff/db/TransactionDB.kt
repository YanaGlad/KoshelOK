package com.example.gladkikhvlasovtinkoff.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData

@Entity(tableName = "transaction")
data class TransactionDB (
    @PrimaryKey
    val id : Long,
    val date : Long,
    val walletId : Long,
    val isIncome : Boolean,
    val amount : String,
    @ColumnInfo(name ="currency_id")
    val currencyId: Long,
    @Embedded
    val transactionCategoryData: CategoryDB
    )
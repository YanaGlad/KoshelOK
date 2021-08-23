package com.example.gladkikhvlasovtinkoff.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class WalletDB(
    @PrimaryKey
    val id : Long,
    val userId : Long,
    val name : String,
    val limit : String,
    val amount : String,
    @ColumnInfo(name="currency_id")
    val currencyId : Long
)
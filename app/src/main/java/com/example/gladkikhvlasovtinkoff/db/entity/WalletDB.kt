package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.*

@Entity(tableName = "wallet")
data class WalletDB(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val username: String,
    val name: String,
    val limit: String,
    val amount: String,
    @Embedded
    val currency: CurrencyDB
)
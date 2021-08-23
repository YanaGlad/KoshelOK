package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB


data class WalletWithCurrency (
    val id : Long,
    val userId : Long,
    val name : String,
    val limit : String,
    val amount : String,
    @ColumnInfo(name="currency_id")
    val currencyId : Long,
    @Relation(parentColumn = "currency_id", entityColumn = "id", entity = CurrencyDB::class)
    val currency : CurrencyDB
)
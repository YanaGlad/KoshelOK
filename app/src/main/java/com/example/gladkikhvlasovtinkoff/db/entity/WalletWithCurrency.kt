package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.Relation
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB


data class WalletWithCurrency (
    val id : Long,
    val userId : Long,
    val name : String,
    val limit : String,
    val amount : String,
    @Relation(parentColumn = "currency_id", entityColumn = "id", entity = CurrencyDB::class)
    val currency : CurrencyDB
)
package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB

data class TransactionWithCurrency (
    @PrimaryKey
    val id : Long,
    val date : Long,
    val walletId : Long,
    val isIncome : Boolean,
    val amount : String,
    @Relation(parentColumn = "currency_id", entityColumn = "id", entity = CurrencyDB::class)
    val currencyId: Long,
    val transactionCategoryData: CategoryDB
)
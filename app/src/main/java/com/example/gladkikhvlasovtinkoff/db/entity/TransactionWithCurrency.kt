package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
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
    @ColumnInfo(name="currency_id")
    val currencyId : Long,
    @Relation(parentColumn = "currency_id", entityColumn = "id", entity = CurrencyDB::class)
    val currency: CurrencyDB,

    @Relation(parentColumn = "currency_id", entityColumn = "category_id", entity = CategoryDB::class)
    val transactionCategoryData: CategoryDB
)
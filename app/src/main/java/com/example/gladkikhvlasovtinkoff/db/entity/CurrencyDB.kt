package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyDB (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "currency_id")
    val id : Long,
    val code : String,
    @ColumnInfo(name = "currency_name")
    val name : String
    )
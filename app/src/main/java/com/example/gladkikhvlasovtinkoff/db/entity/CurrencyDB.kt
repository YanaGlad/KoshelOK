package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyDB (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code")
    val code : String,
    @ColumnInfo(name = "currency_name")
    val name : String
    )
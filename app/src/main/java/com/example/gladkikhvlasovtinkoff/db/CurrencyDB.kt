package com.example.gladkikhvlasovtinkoff.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyDB (
    @PrimaryKey
    val id : Long,
    val code : String,
    val name : String
    )
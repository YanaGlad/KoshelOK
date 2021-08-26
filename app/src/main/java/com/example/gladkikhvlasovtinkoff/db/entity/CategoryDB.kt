package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDB(
    @PrimaryKey
    @ColumnInfo(name ="user_name")
    val userName : String,
    val name: String,
    val stringId: String,
    val description: String,
    val colorRed: Int,
    val colorBlue: Int,
    val colorGreen: Int,
    val income : Boolean
)
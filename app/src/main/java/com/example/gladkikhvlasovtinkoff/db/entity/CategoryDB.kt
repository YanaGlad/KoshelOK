package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "category",
    primaryKeys = ["user_name", "category_id"]
)
data class CategoryDB(
    @ColumnInfo(name = "user_name")
    val username: String,
    val name: String,
    val stringId: String,
    val description: String,
    val colorRed: Int,
    val colorBlue: Int,
    val colorGreen: Int,
    val income: Boolean,
    @ColumnInfo(name = "category_id")
    val id: Long
)

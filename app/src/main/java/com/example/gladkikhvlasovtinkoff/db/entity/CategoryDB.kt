package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDB(
    @PrimaryKey
    @ColumnInfo(name ="category_id")
    val id : Long,
    val name: String,
    val iconId: Int,
    val description: String,
    val color: Int
)
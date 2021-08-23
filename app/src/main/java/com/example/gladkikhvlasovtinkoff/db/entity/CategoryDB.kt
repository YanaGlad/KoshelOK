package com.example.gladkikhvlasovtinkoff.db.entity

import androidx.room.PrimaryKey

data class CategoryDB(
    val name: String,
    val iconId: Int,
    val description: String,
    val color: Int
)
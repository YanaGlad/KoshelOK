package com.example.gladkikhvlasovtinkoff.model

import android.content.Context

interface TransactionCategoryDataFactory {
    fun getCategories(context : Context) : List<TransactionCategoryData>
}

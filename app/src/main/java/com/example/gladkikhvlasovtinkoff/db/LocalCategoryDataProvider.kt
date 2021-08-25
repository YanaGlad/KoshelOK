package com.example.gladkikhvlasovtinkoff.db

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalCategoryDataProvider {
    fun insertCategory(item : CategoryDataSample)
    fun insertAllCategories(items : List<CategoryDataSample>)
    fun getAllTransactionsIncome() : Flowable<List<CategoryDataSample>>
    fun getAllTransactionsExpense() : Flowable<List<CategoryDataSample>>
    fun getCategoryById(id : Long) : Single<CategoryDataSample>
    fun deleteCategory(item : CategoryDataSample)
    fun updateCategory(item : CategoryDataSample)
}
package com.example.gladkikhvlasovtinkoff.db.dataprovider

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalCategoryDataProvider {
    fun insertCategory(item : CategoryDataSample)
    fun insertAllCategories(items : List<CategoryDataSample>)
    fun getAllCategoriesByIncome(income : Boolean) : Flowable<List<CategoryDataSample>>
    fun getAllCategories() : Flowable<List<CategoryDataSample>>
    fun deleteCategory(item : CategoryDataSample)
    fun updateCategory(item : CategoryDataSample)
}
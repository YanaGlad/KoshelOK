package com.example.gladkikhvlasovtinkoff.db.dataprovider

import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import io.reactivex.Flowable

interface LocalCategoryDataProvider {
    fun insertCategory(item : CategoryDataSample)
    fun insertAllCategories(items : List<CategoryDataSample>)
    fun getAllCategoriesByIncome(income : Boolean) : Flowable<List<CategoryDataSample>>
    fun getAllCategories() : Flowable<List<CategoryDataSample>>
    fun deleteCategory(item : CategoryDataSample)
    fun updateCategory(item : CategoryDataSample)

}

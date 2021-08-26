package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.CategoryListViewState
import io.reactivex.Flowable
import io.reactivex.Single

interface CategoryRepository {
    fun createCategory(categorySample: CategoryDataSample): Single<CategoryListViewState>
    fun getCategories(income : Boolean) : Flowable<CategoryListViewState>
    fun loadCategories(context : Context) : Single<CategoryListViewState>
}
package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.CategoryListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.CategoryViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.Path

interface CategoryRepository {
    fun createCategory(categorySample: CategoryDataSample): Single<CategoryListViewState>
    fun getCategories(income: Boolean): Flowable<CategoryListViewState>
    fun loadCategories(context: Context): Single<CategoryListViewState>
    fun deleteCategory(categorySample: CategoryDataSample): Single<CategoryListViewState>
}
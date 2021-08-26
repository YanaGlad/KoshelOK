package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.CategoryListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.CategoryViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CategoryRepository {
    fun createCategory(categorySample: CategoryDataSample): Completable
    fun getCategories(income : Boolean) : Flowable<CategoryListViewState>
    fun loadCategories() : Single<CategoryListViewState>
}
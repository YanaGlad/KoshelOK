package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import io.reactivex.Completable

interface CategoryRepository {
    fun createCategory(categorySample: CategoryDataSample): Completable
}
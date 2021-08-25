package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import com.example.gladkikhvlasovtinkoff.network.wallet.response.CategoryResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryRepository {
    fun createCategory(categorySample: CategoryDataSample): Completable
}
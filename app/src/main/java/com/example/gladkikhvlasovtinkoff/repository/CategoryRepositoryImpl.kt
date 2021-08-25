package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.db.LocalCategoryDataProvider
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import io.reactivex.Completable
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localWalletDataProvider: LocalCategoryDataProvider
) : CategoryRepository {

    override fun createCategory(categorySample: CategoryDataSample): Completable =
        remoteWalletDataProvider.createCategory(
            CategoryRequest(
                blue = categorySample.colorBlue,
                type = categorySample.description,
                green = categorySample.colorGreen,
                isIncome = categorySample.income,
                name = categorySample.name,
                red = categorySample.colorRed,
                stringId = categorySample.stringId
            )
        ).flatMapCompletable { category ->
            Completable.create { emitter ->
                try {
                    localWalletDataProvider
                        .insertCategory(category)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }



}
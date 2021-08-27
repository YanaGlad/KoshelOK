package com.example.gladkikhvlasovtinkoff.repository

import android.content.Context
import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.dataprovider.LocalCategoryDataProvider
import com.example.gladkikhvlasovtinkoff.extension.getIconIdByNameId
import com.example.gladkikhvlasovtinkoff.extension.getNameIdByStringId
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData.Companion.PUBLIC_CATEGORY_USER

import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.CategoryListViewState
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteWalletDataProvider: RemoteWalletDataProvider,
    private val localCategoryDataProvider: LocalCategoryDataProvider,
    private val authDataHolder: AuthDataHolder
) : CategoryRepository {

    override fun createCategory(categorySample: CategoryDataSample): Single<CategoryListViewState> =
        Single.create { emitter ->
            val request = CategoryRequest(
                blue = categorySample.colorBlue,
                username = categorySample.username,
                green = categorySample.colorGreen,
                isIncome = categorySample.income,
                name = categorySample.name,
                red = categorySample.colorRed,
                stringId = categorySample.stringId
            )
            remoteWalletDataProvider.createCategory(request)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    { category ->
                        localCategoryDataProvider
                            .insertCategory(category)
                        emitter.onSuccess(CategoryListViewState.SuccessOperation)
                    },
                    { throwable ->
                        emitter.onSuccess(throwable.convertToViewState())
                    }
                )
        }

    override fun getCategories(income : Boolean): Flowable<CategoryListViewState> =
        if (authDataHolder.isAuth())
            localCategoryDataProvider
                .getAllCategories()
                .map { samples ->
                    CategoryListViewState.Loaded(
                        samples.map{ item ->
                            TransactionCategoryData(
                                name = item.name,
                                iconId = getIconIdByNameId(item.stringId),
                                userName = item.username ?: PUBLIC_CATEGORY_USER,
                                description = item.username,
                                colorRed = item.colorRed,
                                colorBlue = item.colorBlue,
                                colorGreen = item.colorGreen,
                                income = item.income,
                                id = item.id
                            )
                        }.filter { it.income == income }
                    )
                }
        else
            Flowable.just(CategoryListViewState.Error.AuthError)

    override fun loadCategories(context : Context): Single<CategoryListViewState> =
        Single.create { emitter ->
            if (authDataHolder.isAuth()) {
                val authKey = authDataHolder.getUserKey()
                remoteWalletDataProvider.getAllCategoriesByUsername(authKey)
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { categories ->
                            localCategoryDataProvider.insertAllCategories(
                                categories.map { category ->
                                    CategoryDataSample(
                                        username = authKey,
                                        name = if (category.username == PUBLIC_CATEGORY_USER)
                                            getNameIdByStringId(category.stringId, context)
                                        else category.name,
                                        stringId = category.stringId,
                                        colorRed = category.colorRed,
                                        colorBlue = category.colorBlue,
                                        colorGreen = category.colorGreen,
                                        income = category.income,
                                        id = category.id
                                    )
                                }
                            )
                            emitter.onSuccess(CategoryListViewState.SuccessOperation)
                        },
                        { throwable ->
                            emitter.onSuccess(throwable.convertToViewState())
                        }
                    )
            }
            else
                emitter.onSuccess(CategoryListViewState.Error.AuthError)
        }

    private fun Throwable.convertToViewState(): CategoryListViewState =
        when (this) {
            is IOException -> CategoryListViewState.Error.NetworkError
            else -> CategoryListViewState.Error.UnexpectedError
        }
}
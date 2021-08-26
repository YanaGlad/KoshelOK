package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.auth.AuthDataHolder
import com.example.gladkikhvlasovtinkoff.db.LocalCategoryDataProvider
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.request.CategoryRequest
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.CategoryListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.CategoryViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import io.reactivex.Completable
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
                    localCategoryDataProvider
                        .insertCategory(category)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }

    override fun getCategories(): Flowable<CategoryListViewState> =
        if (authDataHolder.isAuth())
            localCategoryDataProvider
                .getCategoriesByUsername(authDataHolder.getUserKey())
                .map {
                    CategoryListViewState.Loaded(it)
                }
        else
            Flowable.just(CategoryListViewState.Error.AuthError)

    override fun loadCategories(): Single<CategoryListViewState> =
        Single.create { emitter ->
            if (authDataHolder.isAuth())
                remoteWalletDataProvider.getAllCategoriesByUsername(
                    authDataHolder.getUserKey()
                )
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { categories ->
                            localCategoryDataProvider.insertAllCategories(
                                categories.map { category ->
                                    CategoryDataSample(
                                        userName = category.userName,
                                        name = category.name,
                                        stringId = category.stringId,
                                        description = category.description,
                                        colorRed = category.colorRed,
                                        colorBlue = category.colorBlue,
                                        colorGreen = category.colorGreen,
                                        income = category.income
                                    )
                                }
                            )
                            emitter.onSuccess(CategoryListViewState.SuccessOperation)

                        },
                        { throwable ->
                            emitter.onSuccess(throwable.convertToViewState())
                        }
                    )
            else
                emitter.onSuccess(CategoryListViewState.Error.AuthError)


        }

    private fun Throwable.convertToViewState(): CategoryListViewState =
        when (this) {
            is IOException -> CategoryListViewState.Error.NetworkError
            else -> CategoryListViewState.Error.UnexpectedError
        }
}
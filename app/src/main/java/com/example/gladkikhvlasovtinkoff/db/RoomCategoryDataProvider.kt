package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData.Companion.PUBLIC_CATEGORY_USER
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCategoryDataProvider @Inject constructor(val dao: CategoryDao) :
    LocalCategoryDataProvider {

    override fun insertCategory(item: CategoryDataSample) {
        dao.insertCategory(
            CategoryDB(
                item.userName ?: PUBLIC_CATEGORY_USER,
                item.name,
                item.stringId,
                item.description,
                item.colorRed,
                item.colorBlue,
                item.colorGreen,
                item.income
            )
        )
    }

    override fun insertAllCategories(items: List<CategoryDataSample>) =
        dao.insertAllCategories(
            items.map { item ->
                CategoryDB(
                    item.userName ?: PUBLIC_CATEGORY_USER,
                    item.name,
                    item.stringId,
                    item.description,
                    item.colorRed,
                    item.colorBlue,
                    item.colorGreen,
                    item.income
                )
            }
        )


    override fun getAllCategoriesByIncome(income: Boolean): Flowable<List<CategoryDataSample>> =
        dao.getAllCategoriesByIncome(income)
            .map { categories ->
                categories.map { item ->
                    CategoryDataSample(
                        item.userName,
                        item.name,
                        item.stringId,
                        item.description,
                        item.colorRed,
                        item.colorBlue,
                        item.colorGreen,
                        item.income
                    )
                }
            }

    override fun getAllCategories(): Flowable<List<CategoryDataSample>> =
        dao.getAllCategories()
            .map { categories ->
                categories.map { item ->
                    CategoryDataSample(
                        item.userName,
                        item.name,
                        item.stringId,
                        item.description,
                        item.colorRed,
                        item.colorBlue,
                        item.colorGreen,
                        item.income
                    )
                }
            }

    override fun deleteCategory(item: CategoryDataSample) {
        dao.deleteCategory(
            CategoryDB(
                item.userName ?: PUBLIC_CATEGORY_USER,
                item.name,
                item.stringId,
                item.description,
                item.colorRed,
                item.colorBlue,
                item.colorGreen,
                item.income
            )
        )
    }

    override fun updateCategory(item: CategoryDataSample) =
        dao.updateCategory(
            CategoryDB(
                item.userName ?: PUBLIC_CATEGORY_USER,
                item.name,
                item.stringId,
                item.description,
                item.colorRed,
                item.colorBlue,
                item.colorGreen,
                item.income
            )
        )

}
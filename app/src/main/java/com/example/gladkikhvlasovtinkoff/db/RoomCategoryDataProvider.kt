package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomCategoryDataProvider @Inject constructor(val dao: CategoryDao) :
    LocalCategoryDataProvider {

    override fun insertCategory(item: CategoryDataSample) {
        dao.insertCategory(
            CategoryDB(
                item.id,
                item.name,
                item.stringId,
                item.description,
                item.colorRed,
                item.colorBlue,
                item.colorGreen
            )
        )
    }

    override fun insertAllCategories(items: List<CategoryDataSample>) {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionsIncome(): Flowable<List<CategoryDataSample>> {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionsExpense(): Flowable<List<CategoryDataSample>> {
        TODO("Not yet implemented")
    }

    override fun getCategoryById(id: Long): Single<CategoryDataSample> {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(item: CategoryDataSample) {
        TODO("Not yet implemented")
    }

    override fun updateCategory(item: CategoryDataSample) {
        TODO("Not yet implemented")
    }

}
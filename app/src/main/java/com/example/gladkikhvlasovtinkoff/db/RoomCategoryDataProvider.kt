package com.example.gladkikhvlasovtinkoff.db

import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.extension.getIconIfByCategoryName
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.WalletData
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomCategoryDataProvider @Inject constructor(val dao: CategoryDao) :
    LocalCategoryDataProvider {

    override fun insertCategory(item: CategoryDataSample) {
        dao.insertCategory(
            CategoryDB(
                item.userName,
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

    override fun insertAllCategories(items: List<CategoryDataSample>) {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionsIncome(): Flowable<List<CategoryDataSample>> {
        TODO("Not yet implemented")
    }

    override fun getAllTransactionsExpense(): Flowable<List<CategoryDataSample>> {
        TODO("Not yet implemented")
    }


    override fun deleteCategory(item: CategoryDataSample) {
        TODO("Not yet implemented")
    }

    override fun updateCategory(item: CategoryDataSample) {
        TODO("Not yet implemented")
    }

    override fun getCategoriesByUsername(username: String): Flowable<List<TransactionCategoryData>> =
       dao.getCategoriesByUsername(username) .map { categoryDb ->
           categoryDb
               .map { category ->
                   TransactionCategoryData(
                       userName = category.userName,
                       name = category.name,
                       iconId = getIconIfByCategoryName(category.stringId),
                       description = category.description,
                       colorRed = category.colorRed,
                       colorBlue = category.colorBlue,
                       colorGreen = category.colorGreen,
                       income = category.income
                   )
               }
       }


}
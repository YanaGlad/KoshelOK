package com.example.gladkikhvlasovtinkoff.db

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(item : CategoryDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(items : List<CategoryDB>)

    @Query(value = "Select * from `category` where description = 'Доходы'")
    fun getAllTransactionsIncome() : Flowable<List<CategoryDB>>

    @Query(value = "Select * from `category` where description = 'Расходы'")
    fun getAllTransactionsExpense() : Flowable<List<CategoryDB>>

    @Query(value = "Select * from `category` where category_id =:id")
    fun getCategoryById(id : Long) : Single<CategoryDB>

    @Delete()
    fun deleteCategory(item : CategoryDB)

    @Update
    fun updateCategory(item : CategoryDB)
}
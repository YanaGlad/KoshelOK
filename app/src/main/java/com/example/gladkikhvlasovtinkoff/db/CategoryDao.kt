package com.example.gladkikhvlasovtinkoff.db

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.CategoryDB
import com.example.gladkikhvlasovtinkoff.db.entity.WalletDB
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

    @Query(value = "Select * from `category` where user_name =:username")
    fun getCategoriesByUsername(username : String) : Flowable<List<CategoryDB>>

    @Delete()
    fun deleteCategory(item : CategoryDB)

    @Update
    fun updateCategory(item : CategoryDB)
}
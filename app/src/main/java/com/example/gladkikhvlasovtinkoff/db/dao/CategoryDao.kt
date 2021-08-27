package com.example.gladkikhvlasovtinkoff.db.dao

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

    @Query(value = "Select * from `category` where income = :income " )
    fun getAllCategoriesByIncome(income : Boolean) : Flowable<List<CategoryDB>>

    @Query("Select * from `category`" )
    fun getAllCategories() : Flowable<List<CategoryDB>>

    @Delete()
    fun deleteCategory(item : CategoryDB)

    @Update
    fun updateCategory(item : CategoryDB)
}
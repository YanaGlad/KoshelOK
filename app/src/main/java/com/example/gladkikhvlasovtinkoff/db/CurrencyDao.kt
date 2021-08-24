package com.example.gladkikhvlasovtinkoff.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gladkikhvlasovtinkoff.db.entity.CurrencyDB
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CurrencyDao {
    @Query("Select * from currency")
    fun getCurrencies() : Flowable<List<CurrencyDB>>

    @Query("Select * from currency where code = :code")
    fun getCurrencyByCode(code : String) : Single<CurrencyDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies : List<CurrencyDB>)
}
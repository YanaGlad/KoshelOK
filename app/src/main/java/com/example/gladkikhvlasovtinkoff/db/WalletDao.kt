package com.example.gladkikhvlasovtinkoff.db

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.WalletDB
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface WalletDao {

    @Query( value ="Select * from wallet where userId = :userId")
    fun getWalletsByUserId( userId : Long) : Flowable<List<WalletDB>>

    @Query("Select * from wallet where id = :id")
    fun getWalletById(id : Long) : Single<WalletDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallet(walletDB: WalletDB)

    @Delete
    fun deleteWallet(walletDB: WalletDB)

    @Update
    fun updateWallet(walletDB: WalletDB)
}
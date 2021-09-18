package com.example.gladkikhvlasovtinkoff.db.dao

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.WalletDB
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface WalletDao {

    @Query( value ="Select * from wallet where username = :username")
    fun getWalletsByUsername(username : String) : Flowable<List<WalletDB>>

    @Query("Select * from wallet where id = :id")
    fun getWalletById(id : Long) : Single<WalletDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallet(walletDB: WalletDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWallets(walletDB: List<WalletDB>)

    @Delete
    fun deleteWallet(walletDB: WalletDB)

    @Update
    fun updateWallet(walletDB: WalletDB)
}
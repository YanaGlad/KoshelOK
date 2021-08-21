package com.example.gladkikhvlasovtinkoff.di

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object RoomModule {
//
//    @Provides
//    @Singleton
//    fun getDatabase(@ApplicationContext context: Context): LocalDatabase {
//        return Room.databaseBuilder(
//            context,
//            LocalDatabase::class.java,
//            "wallet"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun getTransactions(database: LocalDatabase): TransactionDao {
//        return database.getTransactionDao()
//    }
//
//}
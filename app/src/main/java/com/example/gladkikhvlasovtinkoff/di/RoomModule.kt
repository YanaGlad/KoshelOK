package com.example.gladkikhvlasovtinkoff.di

import android.content.Context
import androidx.room.Room
import com.example.gladkikhvlasovtinkoff.db.WalletDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideWalletDatabase(@ApplicationContext context : Context) : WalletDatabase =
        Room.databaseBuilder(context, WalletDatabase::class.java, "wallet-db").build()

    @Provides
    @Singleton
    fun provideWalletDao(database: WalletDatabase) = database.walletDao()

    @Provides
    @Singleton
    fun provideCurrencyDao(database: WalletDatabase) = database.currencyDao()

    @Provides
    @Singleton
    fun provideTransactionDao(database: WalletDatabase) = database.transactionDao()
}
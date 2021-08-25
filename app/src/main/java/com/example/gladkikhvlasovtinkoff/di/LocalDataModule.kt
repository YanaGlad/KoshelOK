package com.example.gladkikhvlasovtinkoff.di

import com.example.gladkikhvlasovtinkoff.db.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataModule {

    @Binds
    @Singleton
    fun bindTransactionDataProvider(roomTransactionDataProvider: RoomTransactionDataProvider)
    : LocalTransactionDataProvider

    @Binds
    @Singleton
    fun bindWalletDataProvider(roomWalletDataProvider: RoomWalletDataProvider)
    : LocalWalletDataProvider

    @Binds
    @Singleton
    fun bindCurrencyDataProvider(roomCurrencyDataProvider: RoomCurrencyDataProvider)
    : LocalCurrencyDataProvider

    @Binds
    @Singleton
    fun bindAuthProvider(roomAuthProvider: RoomAuthProvider) : LocalAuthProvider
}

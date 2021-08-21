package com.example.gladkikhvlasovtinkoff.di

import com.example.gladkikhvlasovtinkoff.network.wallet.ApiWalletDataProvider
import com.example.gladkikhvlasovtinkoff.network.wallet.RemoteWalletDataProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataModule {

    @Binds
    @Reusable
    fun bindRemoteWalletDataProvider(apiWalletDataProvider: ApiWalletDataProvider) : RemoteWalletDataProvider

}
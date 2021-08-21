package com.example.gladkikhvlasovtinkoff.di

import com.example.gladkikhvlasovtinkoff.repository.TransactionRepository
import com.example.gladkikhvlasovtinkoff.repository.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun getTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl) : TransactionRepository


}
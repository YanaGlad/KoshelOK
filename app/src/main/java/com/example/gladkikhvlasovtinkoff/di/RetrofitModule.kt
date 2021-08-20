package com.example.gladkikhvlasovtinkoff.di

import com.example.gladkikhvlasovtinkoff.api.TransactionApi
import com.example.gladkikhvlasovtinkoff.extension.TRANSACTION_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    @Named("TRANSACTION")
    fun getTransactionRetrofit(): Retrofit {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        return Retrofit.Builder()
            .baseUrl(TRANSACTION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun getTransactionApi(@Named("TRANSACTION") retrofit: Retrofit): TransactionApi {
        return retrofit.create(TransactionApi::class.java)
    }

}
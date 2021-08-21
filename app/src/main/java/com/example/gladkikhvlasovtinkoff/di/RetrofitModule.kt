package com.example.gladkikhvlasovtinkoff.di

import com.example.gladkikhvlasovtinkoff.extension.TRANSACTION_BASE_URL
import com.example.gladkikhvlasovtinkoff.network.wallet.TransactionApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    private val  CONNECT_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 30L
    private val READ_TIMEOUT = 10L

    @Provides
    @Reusable
    fun provideJson() = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Reusable
    fun provideHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Reusable
    @Named("TRANSACTION")
    fun getTransactionRetrofit(httpClient: OkHttpClient, json : Json): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(TRANSACTION_BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getTransactionApi(@Named("TRANSACTION") retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)


}
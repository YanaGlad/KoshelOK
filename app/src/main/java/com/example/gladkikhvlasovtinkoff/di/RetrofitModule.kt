package com.example.gladkikhvlasovtinkoff.di

import com.example.gladkikhvlasovtinkoff.extension.TRANSACTION_BASE_URL
import com.example.gladkikhvlasovtinkoff.network.wallet.TransactionApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    private val CONNECT_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 30L
    private val READ_TIMEOUT = 10L


    @Provides
    @Reusable
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Reusable
    @Named("TRANSACTION")
    fun getTransactionRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(TRANSACTION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getTransactionApi(@Named("TRANSACTION") retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

}
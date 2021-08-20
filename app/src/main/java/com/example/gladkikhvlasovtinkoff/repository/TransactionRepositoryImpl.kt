package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.api.TransactionApi
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    val transactionApi: TransactionApi
) : TransactionRepository{
}
package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.api.TransactionApi
import com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation.WalletTransactionData
import retrofit2.Call
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    val transactionApi: TransactionApi
) : TransactionRepository{
}
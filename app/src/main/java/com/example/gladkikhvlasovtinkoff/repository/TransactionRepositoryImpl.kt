package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.api.TransactionApi
import com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation.WalletOperationModel
import retrofit2.Call
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    val transactionApi: TransactionApi
) : TransactionRepository{
    override fun getTransaction(): Call<WalletOperationModel>  = transactionApi.getTransaction()

}
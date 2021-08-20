package com.example.gladkikhvlasovtinkoff.repository

import com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation.WalletOperationModel
import retrofit2.Call
import retrofit2.Response

interface TransactionRepository {
    fun getTransaction(): Call<WalletOperationModel>
}
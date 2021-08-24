package com.example.gladkikhvlasovtinkoff.network.wallet

import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.network.wallet.response.CurrencyResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.UserResponse
import com.example.gladkikhvlasovtinkoff.network.wallet.response.WalletResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface RemoteWalletDataProvider {
 
    fun findWalletById(walletId: Long): Single<WalletData>
    fun getAllWalletByUserId(userId: Long): Single<List<WalletData>>
    fun createWallet(walletId: Long): Single<WalletData>
    fun deleteWallet(walletResponse: WalletResponse)
    fun updateWallet(walletId: Long, name: String): Single<WalletData>
 
//    fun findWalletById(walletId : Long) : Single<WalletData>
//    fun getAllWalletByUserId(userId : Long) : Single<List<WalletData>>
 
//    fun getCurrencyById(currencyId : Long) : Single<CurrencyResponse>
//    fun getAllCurrencies() : Single<List<CurrencyResponse>>
//    fun getUserByUsername(username : String) : Single<UserResponse>

}

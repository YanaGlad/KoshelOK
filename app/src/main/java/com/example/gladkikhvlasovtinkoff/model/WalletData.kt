package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalletData (
    val id : Long,
    val username : String,
    val name : String,
    val limit : String,
    val amount : String,
    val currency : Currency
    ) : Parcelable{
        companion object{
            const val INFINITE_LIMIT = "0"
        }
        fun toWalletDataSample() : WalletDataSample{
            return WalletDataSample(id,username,name,limit,amount,currency)
        }
    }


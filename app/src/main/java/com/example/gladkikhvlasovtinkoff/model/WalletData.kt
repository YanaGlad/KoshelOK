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
    val currency : Currency,
    val hidden : Boolean
    ) : Parcelable{

        fun toWalletDataSample() : WalletDataSample{
            return WalletDataSample(id,username,name,limit,amount,currency)
        }
    }


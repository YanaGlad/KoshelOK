package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class WalletDataSample(
    var id: Long = UNDEFINED_ID.toLong(),
    var username: String = UNDEFINED_STR,
    var name: String = UNDEFINED_STR,
    var limit: String = UNDEFINED_STR,
    var amount: String = UNDEFINED_STR,
    var currency: Currency = Currency( UNDEFINED_STR, UNDEFINED_STR),
    var hidden : Boolean = false
) : Parcelable {
    fun createWalletDataModel(): WalletData {
        return WalletData(id, username, name, limit, amount, currency, hidden)
    }
}
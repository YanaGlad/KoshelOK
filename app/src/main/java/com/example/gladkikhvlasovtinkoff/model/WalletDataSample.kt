package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class WalletDataSample(
    var id: Long = UNDEFINED_ID.toLong(),
    var userId: Long = UNDEFINED_ID.toLong(),
    var name: String = UNDEFINED_STR,
    var limit: Long = UNDEFINED_ID.toLong(),
    var amount: String = UNDEFINED_STR,
    var currency: Currency = Currency(UNDEFINED_STR, UNDEFINED_STR)
) : Parcelable {
    fun createWalletDataModel(): WalletData {
        return WalletData(id, userId, name, limit, amount, currency)
    }
}
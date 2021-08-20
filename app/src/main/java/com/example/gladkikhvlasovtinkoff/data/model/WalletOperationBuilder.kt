package com.example.gladkikhvlasovtinkoff.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class WalletOperationBuilder(
    var date: Long = -1,
    var imageId: Int = -1,
    var categoryTextId: Int = -1,
    var type: String = "",
    var value: String = ""
) : Parcelable {
    fun createModel(): WalletTransactionData {
        return WalletTransactionData(date, imageId, categoryTextId, type, value)
    }
}
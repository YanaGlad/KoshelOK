package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation.WalletOperationData
import kotlinx.parcelize.Parcelize

@Parcelize
class WalletOperationBuilder(
    var date: Long = -1,
    var imageId: Int = -1,
    var categoryTextId: Int = -1,
    var type: String = "",
    var value: String = ""
) : Parcelable {
    fun createModel(): WalletOperationData {
        return WalletOperationData(date, imageId, categoryTextId, type, value)
    }
}
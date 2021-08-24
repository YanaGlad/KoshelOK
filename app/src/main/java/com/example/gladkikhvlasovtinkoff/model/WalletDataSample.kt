package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletsViewModel.Companion.TEMP_USER_ID
import kotlinx.parcelize.Parcelize

@Parcelize
class WalletDataSample(
    var id: Long = UNDEFINED_ID.toLong(),
    var userId: Long = TEMP_USER_ID,
    var name: String = UNDEFINED_STR,
    var limit: String = UNDEFINED_STR,
    var amount: String = UNDEFINED_STR,
    var currency: Currency = Currency(UNDEFINED_ID.toLong(), UNDEFINED_STR, UNDEFINED_STR)
) : Parcelable {
    fun createWalletDataModel(): WalletData {
        return WalletData(id, userId, name, limit, amount, currency)
    }
}
package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import com.example.gladkikhvlasovtinkoff.extension.getIconIdByNameId
import kotlinx.parcelize.Parcelize


@Parcelize
class CategoryDataSample(
    var id: Long = UNDEFINED_ID.toLong(),
    var name: String = "Новая категория",
    var stringId: String = UNDEFINED_STR,
    var username: String = UNDEFINED_STR,
    var colorRed: Int = 0,
    var colorBlue: Int = 0,
    var colorGreen: Int = 0,
    var income: Boolean = true,
) : Parcelable {
    public fun createCategoryData(): TransactionCategoryData {
        return TransactionCategoryData(
            id = id,
            name =  name,
            iconId = getIconIdByNameId(stringId),
            userName = username,
            colorRed = colorRed,
            colorBlue = colorBlue,
            colorGreen = colorGreen,
            income = income,
            description = name
        )
    }
}
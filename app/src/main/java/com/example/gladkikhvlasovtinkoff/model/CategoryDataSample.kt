package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class CategoryDataSample(
    var id : Long = UNDEFINED_ID.toLong(),
    var name: String = "Новая категория",
    var stringId: String = UNDEFINED_STR,
    var description: String = UNDEFINED_STR,
    var colorRed: Int = UNDEFINED_ID,
    var colorBlue: Int = UNDEFINED_ID,
    var colorGreen: Int = UNDEFINED_ID,
    var income : Boolean = true
) : Parcelable
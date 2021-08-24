package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionCategoryData (
    val name : String,
    val iconId : Int,
    val id : Long ,
    val description : String,
    val colorRed: Int,
    val colorBlue: Int,
    val colorGreen: Int
) : Parcelable

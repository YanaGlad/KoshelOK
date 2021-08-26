package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionCategoryData (
    val name : String,
    val iconId : Int,
    val userName : String ,
    val description : String,
    val colorRed: Int,
    val colorBlue: Int,
    val colorGreen: Int,
    val income : Boolean
) : Parcelable

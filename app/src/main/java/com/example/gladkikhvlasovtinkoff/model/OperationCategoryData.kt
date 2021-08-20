package com.example.gladkikhvlasovtinkoff.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class OperationCategoryData (
    val name : String,
    val iconId : Int,
    val id : Int ,
    val description : String
    ) : Parcelable
